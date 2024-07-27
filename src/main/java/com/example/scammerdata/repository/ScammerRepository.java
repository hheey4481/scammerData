package com.example.scammerdata.repository;

import com.example.scammerdata.domain.Scammer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScammerRepository extends JpaRepository<Scammer, Long> {
    List<Scammer> findByNameContainingIgnoreCase(String name);
    List<Scammer> findByAccountContainingIgnoreCase(String account);

    // 계좌번호 중복 확인
    boolean existsByAccount(String account);
}
