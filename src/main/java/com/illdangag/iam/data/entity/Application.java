package com.illdangag.iam.data.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "application",
        indexes = {}
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Application {
    @Id
    @Generated
    @Builder.Default
    private Long id = null;

    @Builder.Default
    @CreationTimestamp
    private LocalDateTime createDate = LocalDateTime.now();

    @Builder.Default
    @Size(max = 100)
    private String name = "";

    @Builder.Default
    @Size(max = 1000)
    private String description = "";
}
