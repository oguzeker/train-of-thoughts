package com.kbox.global.trainofthoughts;

import com.kbox.global.trainofthoughts.configuration.properties.ApplicationProperties;
import com.kbox.global.trainofthoughts.configuration.properties.SwaggerProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EnableConfigurationProperties({
		SwaggerProperties.class,
		ApplicationProperties.class
})
public class TrainOfThoughtsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainOfThoughtsApplication.class, args);
	}

}
