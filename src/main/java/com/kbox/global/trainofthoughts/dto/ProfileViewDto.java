package com.kbox.global.trainofthoughts.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Profile view dto")
public class ProfileViewDto {

    @ApiModelProperty(value = "Id of the history record", example = "1", position = 1)
    private Long profileViewId;

    @ApiModelProperty(value = "Actor of the record", position = 2)
    @JsonIgnoreProperties("profileViews")
    private ProfileDto actor;

    @ApiModelProperty(value = "Date on which the history record was created", position = 3)
    private LocalDateTime createdOn;

}
