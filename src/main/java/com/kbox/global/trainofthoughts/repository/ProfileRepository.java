package com.kbox.global.trainofthoughts.repository;

import com.kbox.global.trainofthoughts.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> getProfileByProfileId(Long profileId);


    /**
     * Tried to build an alternate version of the below query (getProfileWithViewHistory).
     * However, this one EAGERLY fetches all subsequent ProfileViews entities instead of LAZILY.
     * So, this is not what we want to achieve.
     * This method generates the following query:
     *    select
     *    p.profile_id,
     *    p.created_on,
     *    p.fullname
     *            from
     *    profile p
     *    left outer join profile_view pv on
     *    p.profile_id = pv.object_id
     *            where
     *    pv.object_id = ?
     *    and pv.created_on > ?
     */
    Optional<Profile> findByProfileViewsObjectProfileIdAndProfileViewsCreatedOnAfter(
            Long objectId, LocalDateTime dateTimeThreshold);

    /**
     * This method achieves LAZY INITIALIZATION by using a query with "JOIN FETCH"
     */
    @Query("FROM Profile p JOIN FETCH p.profileViews pv WHERE p.profileId = :objectId " +
            "AND pv.createdOn > :dateTimeThreshold")
    Optional<Profile> getProfileWithViewHistory(Long objectId, LocalDateTime dateTimeThreshold);

}
