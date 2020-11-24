package com.kbox.global.trainofthoughts.service.impl;

import com.kbox.global.trainofthoughts.configuration.properties.ApplicationProperties;
import com.kbox.global.trainofthoughts.dto.ProfileDto;
import com.kbox.global.trainofthoughts.dto.ProfileViewDto;
import com.kbox.global.trainofthoughts.dto.ProfileWithViewHistoryDto;
import com.kbox.global.trainofthoughts.entity.ProfileView;
import com.kbox.global.trainofthoughts.repository.ProfileRepository;
import com.kbox.global.trainofthoughts.repository.ProfileViewRepository;
import com.kbox.global.trainofthoughts.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    public static final String ENTITY_NOT_FOUND = "Entity not found";
    public static final int INDEX_ZERO = 0;
    public static final int CONVERT_NUMBER_TO_INDEX = 1;

    private final ProfileRepository profileRepository;
    private final ProfileViewRepository profileViewRepository;
    private final ModelMapper mapper;
    private final ApplicationProperties applicationProperties;

    public ProfileDto getProfile(Long profileId) {
        return mapper.map(profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException(ENTITY_NOT_FOUND)), ProfileDto.class);
    }

    /**
     * This method achieves LAZY INITIALIZATION by using a query with "JOIN FETCH"
     */
    public ProfileWithViewHistoryDto getProfileWithViewHistory(Long profileId) {
        int pageSize = applicationProperties.getPageSize();
        int dateTimePeriod = applicationProperties.getDateTimePeriod();
        LocalDateTime dateTimeThreshold = LocalDateTime.now().minusDays(dateTimePeriod);

        return profileRepository.getProfileWithViewHistory(
                profileId, dateTimeThreshold)
                .map(profile -> {
                    List<ProfileView> list = profile.getProfileViews();
                    list.sort(Comparator.comparing(ProfileView::getCreatedOn));
                    int toIndex = Math.min(pageSize - CONVERT_NUMBER_TO_INDEX, list.size());
                    list.subList(INDEX_ZERO, toIndex);

                    profile.setProfileViews(list);
                    return mapper.map(profile, ProfileWithViewHistoryDto.class);
                })
                .orElseThrow(() -> new RuntimeException(ENTITY_NOT_FOUND));
    }

    /**
     * This method tries to reach the expected LAZY result.
     * However, we are not using LAZY functionality at all here.
     * On the other hand, Transactional annotation is necessary
     * for the Stream returning type of the repository method.
     */
    @Transactional
    public ProfileWithViewHistoryDto getProfileWithViewHistory_Alternate(Long profileId) {
        int pageSize = applicationProperties.getPageSize();
        int dateTimePeriod = applicationProperties.getDateTimePeriod();
        LocalDateTime dateTimeThreshold = LocalDateTime.now().minusDays(dateTimePeriod);

        ProfileDto profileDto = getProfile(profileId);
        ProfileWithViewHistoryDto response = mapper.map(profileDto, ProfileWithViewHistoryDto.class);
        response.setProfileViews(new ArrayList<>());

        List<ProfileView> profileViews = profileViewRepository.findByObjectProfileIdAndCreatedOnAfter(
                profileId, dateTimeThreshold)
                .limit(pageSize)
                .collect(Collectors.toList());

        profileViews.forEach(profileView -> response.getProfileViews().add(mapper.map(
                profileView, ProfileViewDto.class)));

        return response;
    }

}
