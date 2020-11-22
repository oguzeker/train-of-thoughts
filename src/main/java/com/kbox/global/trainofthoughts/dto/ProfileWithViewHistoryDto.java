package com.kbox.global.trainofthoughts.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Profile dto with view history")
public class ProfileWithViewHistoryDto {

    @ApiModelProperty(value = "Id of the profile entity", example = "1", position = 1)
    private Long profileId;

    @ApiModelProperty(value = "Full name of the person", example = "Tim Marvin", position = 2)
    private String fullname;

    @ApiModelProperty(value = "Date on which the entity was created", example = "2020-11-22 21:18:02", position = 3)
    private LocalDateTime createdOn;

    @ApiModelProperty(value = "View history records for the given entity", position = 4)
    private List<ProfileViewDto> profileViews;

}
