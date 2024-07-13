package com.illdangag.iam.data.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "scope",
        indexes = {}
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Scope {
    @Id
    @GeneratedValue
    @Builder.Default
    private Long id = null;

    @Builder.Default
    @CreationTimestamp
    private LocalDateTime createDate = LocalDateTime.now();

    @Builder.Default
    @Size(max = 100)
    private String name = "";

    @Builder.Default
    @Size(max = 100)
    private String password = "";

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "application_id"),
    })
    @NotNull
    private Application application;
}
