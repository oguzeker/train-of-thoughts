package com.kbox.global.trainofthoughts.repository;

import com.kbox.global.trainofthoughts.entity.ProfileView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public interface ProfileViewRepository extends JpaRepository<ProfileView, Long> {

    /**
     * Here, we are not using LAZY INITIALIZATION functionality at all.
     */
    Stream<ProfileView> findByObjectProfileIdAndCreatedOnAfter(Long objectId, LocalDateTime dateTimeThreshold);

}
