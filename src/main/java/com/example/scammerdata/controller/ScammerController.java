package com.example.scammerdata.controller;

import com.example.scammerdata.domain.Scammer;
import com.example.scammerdata.dto.ScammerDTO;
import com.example.scammerdata.service.ScammerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/scammers")
public class ScammerController {
    private final ScammerService scammerService;

    // 리스트 조회
    @GetMapping
    public String listScammers(Model model) {
        List<Scammer> scammers = scammerService.findAll();
        model.addAttribute("scammers", scammers);
        return "scammerList";
    }
    
    // 등록
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        ScammerDTO scammerDTO = new ScammerDTO();
        scammerDTO.setCount(1); // 기본값 설정
        model.addAttribute("scammer", scammerDTO);
        return "registerScammer";
    }

    @PostMapping
    public String registerScammer(@ModelAttribute ScammerDTO scammerDTO, Model model) {
        if (scammerService.existsByAccount(scammerDTO.getAccount())) {
            model.addAttribute("duplicateAccount", scammerDTO.getAccount());
            model.addAttribute("scammer", scammerDTO); // 모델에 scammer 객체를 추가
            return "registerScammer"; // 중복 계좌번호가 있는 경우 경고 메시지 표시
        }

        Scammer scammer = Scammer.builder()
                .name(scammerDTO.getName())
                .bank(scammerDTO.getBank())
                .account(scammerDTO.getAccount())
                .count(1) // 기본값 1로 설정
                .build();
        scammerService.save(scammer);
        return "redirect:/scammers";
    }

    // 삭제
    @PostMapping("/delete/{id}")
    public String deleteScammer(@PathVariable Long id) {
        scammerService.deleteById(id);
        return "redirect:/scammers";
    }

    // 검색 : 이름, 계좌번호
    @GetMapping("/search")
    public String searchScammers(@RequestParam String searchType, @RequestParam String keyword, Model model) {
        List<Scammer> scammers;
        if ("account".equals(searchType)) {
            scammers = scammerService.findByAccount(keyword);
        } else {
            scammers = scammerService.findByName(keyword);
        }
        model.addAttribute("scammers", scammers);
        return "scammerList";
    }

    //신고 횟수 추가
    @PostMapping("/increment/{id}")
    public String incrementScammerCount(@PathVariable Long id) {
        scammerService.incrementCount(id);
        return "redirect:/scammers";
    }
}
