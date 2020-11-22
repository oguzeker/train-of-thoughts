package com.kbox.global.trainofthoughts.controller.impl;

import com.kbox.global.trainofthoughts.configuration.SwaggerConfiguration;
import com.kbox.global.trainofthoughts.dto.ProfileDto;
import com.kbox.global.trainofthoughts.dto.ProfileWithViewHistoryDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Api(tags = {
        SwaggerConfiguration.TAG_PROFILE
})
public interface ProfileController {

    @ApiOperation(value = "Get Profile By Id", notes = "This endpoint retrieves profile details " +
            "excluding its history records")
    ResponseEntity<ProfileDto> getProfile(@ApiParam(value = "Id of the profile entity") @PathVariable Long profileId);

    @ApiOperation(value = "Get Profile By Id with View History", notes = "This endpoint retrieves profile details " +
            "including its history records")
    ResponseEntity<ProfileWithViewHistoryDto> getProfileWithViewHistory(
            @ApiParam(value = "Id of the profile entity") @PathVariable Long profileId);

}
