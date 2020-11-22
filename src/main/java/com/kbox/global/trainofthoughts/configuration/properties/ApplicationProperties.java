package com.kbox.global.trainofthoughts.configuration.properties;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@Getter
@ConfigurationProperties(prefix = "train-of-thoughts")
public class ApplicationProperties {

    private int pageSize;

    private int dateTimePeriod;

}
