package com.example.scammerdata.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Scammer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id", updatable = false)
    private Long id;

    @Column(name= "name", nullable = false)
    private String name;

    @Column(name = "bank", nullable = false)
    private String bank;

    @Column(name = "account", nullable = false)
    private String account;

    @Column(name = "count", nullable = false)
    private int count = 1; // 기본값을 1로 설정

    @CreatedDate
    @Column(name= "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public void incrementCount() {
        this.count++;
    }
}