package com.example.scammerdata.service;

import com.example.scammerdata.domain.Scammer;
import com.example.scammerdata.repository.ScammerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScammerService {
    private final ScammerRepository scammerRepository;

    public List<Scammer> findAll() {
        return scammerRepository.findAll();
    }

    public Scammer save(Scammer scammer) {
        return scammerRepository.save(scammer);
    }

    public void deleteById(Long id) {
        scammerRepository.deleteById(id);
    }

    public Optional<Scammer> findById(Long id) {
        return scammerRepository.findById(id);
    }

    public List<Scammer> findByName(String name) {
        return scammerRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Scammer> findByAccount(String account) {
        return scammerRepository.findByAccountContainingIgnoreCase(account);
    }

    // 신고 횟수
    public void incrementCount(Long id) {
        Scammer scammer = scammerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid scammer Id:" + id));
        scammer.incrementCount();
        scammerRepository.save(scammer);
    }

    // 계좌번호 중복 확인
    public boolean existsByAccount(String account) {
        return scammerRepository.existsByAccount(account);
    }
}
