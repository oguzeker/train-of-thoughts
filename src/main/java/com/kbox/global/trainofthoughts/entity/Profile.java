package com.kbox.global.trainofthoughts.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileId;

    @Column(nullable = false)
    private String fullname;

    @Column(nullable = false)
    private LocalDateTime createdOn;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "object", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ProfileView> profileViews;

}
