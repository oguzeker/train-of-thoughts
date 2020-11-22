package com.kbox.global.trainofthoughts.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileView {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileViewId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "actor_id")
    private Profile actor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "object_id")
    private Profile object;

    @Column
    private LocalDateTime createdOn;

}
