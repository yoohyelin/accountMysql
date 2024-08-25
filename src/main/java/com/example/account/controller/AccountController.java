package com.example.account.controller;

import com.example.account.domain.Account;
import com.example.account.dto.AccountInfo;
import com.example.account.dto.CreateAccount;
import com.example.account.dto.DeleteAccount;
import com.example.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

// 과거에는 JSP, HTML과 같은 View를 전달해 주었기에 주로 @Controller를 사용했고,
// 최근에는 Frontend / Backend을 나누어 개발하는 경우가 많기에
// Backend에서 Rest Api를 통해 JSON으로 데이터만 전달하기 때문에
// 편리성을 위해 @RestController를 사용한다고 합니다.
@RestController
// Service 객체를 생성자 주입방식으로 DI
//초기화 되지않은 final 필드 생성자를 생성해 줍니다.
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService; // bean등록 되있어야함

    @PostMapping("/account")
    // CreateAccount.Response: 정적 중첩 클래스
    public CreateAccount.Response createAccount(
            @RequestBody @Valid CreateAccount.Request request // 컨트롤러 역할: 파라미터 검증
                                ) {
        return CreateAccount.Response.from(
                accountService.createAccount(
                        request.getUserId(),
                        request.getInitialBalance()
                )
        );
    }

    @GetMapping("/account/{id}")
    public Account getAccount( // 컨트롤러 역할: 요청 분리
            @PathVariable Long id){ // 데이터 1개만 받아올 수 있음
        return accountService.getAccount(id); // 컨트롤러 역할: 비지니스 로직 실행
    }

    @DeleteMapping("/account")
    public DeleteAccount.Response deleteAccount(
            @RequestBody @Valid DeleteAccount.Request request
    ) {
        return DeleteAccount.Response.from(
                accountService.deleteAccount(
                        request.getUserId(),
                        request.getAccountNumber()
                )
        );
    }

    @GetMapping("/account")
    public List<AccountInfo> getAccountByUserId(
            @RequestParam("user_id") Long userId // 여러개 받을 수 있음
    ){
        return accountService.getAccountsByUserId(userId)
                .stream().map(accountDto -> AccountInfo.builder()
                        .accountNumber(accountDto.getAccountNumber())
                        .balance(accountDto.getBalance())
                        .build())
                .collect(Collectors.toList());
    }
}
