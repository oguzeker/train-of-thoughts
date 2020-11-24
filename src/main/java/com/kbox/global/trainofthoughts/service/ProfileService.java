package com.kbox.global.trainofthoughts.service;

import com.kbox.global.trainofthoughts.dto.ProfileDto;
import com.kbox.global.trainofthoughts.dto.ProfileWithViewHistoryDto;

public interface ProfileService {

    ProfileDto getProfile(Long profileId);

    ProfileWithViewHistoryDto getProfileWithViewHistory_Alternate(Long profileId);

    ProfileWithViewHistoryDto getProfileWithViewHistory(Long profileId);

}
