package com.kbox.global.trainofthoughts.service.impl;

import com.github.javafaker.Faker;
import com.kbox.global.trainofthoughts.entity.Profile;
import com.kbox.global.trainofthoughts.entity.ProfileView;
import com.kbox.global.trainofthoughts.repository.ProfileRepository;
import com.kbox.global.trainofthoughts.repository.ProfileViewRepository;
import com.kbox.global.trainofthoughts.service.StartupService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
@AllArgsConstructor
public class StartupServiceImpl implements StartupService {

    private static final int NUMBER_HUNDRED = 100;
    private static final int NUMBER_ZERO = 0;
    private static final int NUMBER_ONE = 1;
    private static final int NUMBER_FIFTY = 50;
    private static final int NUMBER_SIXTY = 60;
    private static final Faker FAKER = new Faker();

    private final ProfileRepository profileRepository;
    private final ProfileViewRepository profileViewRepository;

    @PostConstruct
    public void initialize() {
        persistProfile();
        persistProfileView();
    }

    private void persistProfile() {
        int actorCount = NUMBER_ZERO;
        while (actorCount < NUMBER_HUNDRED) {
            Profile profile = Profile.builder()
                    .fullname(FAKER.name().fullName())
                    .createdOn(LocalDateTime.now())
                    .build();

            profileRepository.save(profile);

            actorCount++;
        }
    }

    private void persistProfileView() {
        Instant now = Instant.now();
        Instant before = now.minus(Duration.ofDays(NUMBER_SIXTY));

        profileRepository.findAll().forEach(actor -> {
            int objectCount = NUMBER_ZERO;
            Profile object = profileRepository.getProfileByProfileId(getRandomLong(NUMBER_ONE, NUMBER_HUNDRED)).get();

            if (!actor.getProfileId().equals(object.getProfileId())) {

                while (objectCount < NUMBER_FIFTY) {
                    ProfileView profileView = ProfileView.builder()
                            .actor(actor)
                            .object(object)
                            .createdOn(getRandomDateTimeBetween(before, now))
                            .build();

                    profileViewRepository.save(profileView);
                    objectCount++;
                }

            }
        });

    }

    private LocalDateTime getRandomDateTimeBetween(Instant originInclusive, Instant boundExclusive) {
        long random = getRandomLong(originInclusive.toEpochMilli(), boundExclusive.toEpochMilli());

        return LocalDateTime.ofInstant(Instant.ofEpochMilli(random), ZoneId.systemDefault());
    }

    private long getRandomLong(long origin, long bound) {
        return ThreadLocalRandom.current().nextLong(origin, bound);
    }

}
