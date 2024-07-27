package com.example.scammerdata.dto;

import com.example.scammerdata.domain.Scammer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ScammerDTO {
    private Long id;
    private String name;
    private String bank;
    private String account;
    private int count = 1;  // 기본값을 1
    private LocalDateTime createdAt;

    public ScammerDTO(Scammer scammer) {
        this.id = scammer.getId();
        this.name = scammer.getName();
        this.bank = scammer.getBank();
        this.account = scammer.getAccount();
        this.count = scammer.getCount();
        this.createdAt = scammer.getCreatedAt();
    }
}
