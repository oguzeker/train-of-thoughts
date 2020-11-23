package com.kbox.global.trainofthoughts.controller.impl;

import com.kbox.global.trainofthoughts.controller.ProfileController;
import com.kbox.global.trainofthoughts.dto.ProfileDto;
import com.kbox.global.trainofthoughts.dto.ProfileWithViewHistoryDto;
import com.kbox.global.trainofthoughts.service.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("profile")
public class ProfileControllerImpl implements ProfileController {

    private final ProfileService profileService;

    @GetMapping("{profileId}")
    public ResponseEntity<ProfileDto> getProfile(@PathVariable Long profileId) {
        return ResponseEntity.ok(profileService.getProfile(profileId));
    }

    @GetMapping("withViewHistory/{profileId}")
    public ResponseEntity<ProfileWithViewHistoryDto> getProfileWithViewHistory(@PathVariable Long profileId) {
        return ResponseEntity.ok(profileService.getProfileWithViewHistory(profileId));
    }

}
