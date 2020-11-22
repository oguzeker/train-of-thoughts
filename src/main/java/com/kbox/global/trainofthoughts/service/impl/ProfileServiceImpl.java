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
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    public static final String ENTITY_NOT_FOUND = "Entity not found";

    private final ProfileRepository profileRepository;
    private final ProfileViewRepository profileViewRepository;
    private final ModelMapper mapper;
    private final ApplicationProperties applicationProperties;

    public ProfileDto getProfile(Long profileId) {
        return mapper.map(profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException(ENTITY_NOT_FOUND)), ProfileDto.class);
    }

    @Transactional
    public ProfileWithViewHistoryDto getProfileWithViewHistory(Long profileId) {
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
