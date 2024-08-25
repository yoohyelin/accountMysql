package com.example.account.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountInfo { // 클라이언트와 컨트롤러 간의 응답, 전용dto 안만들면 복잡해져서 나중에 장애 발생할 수 있음
    private String accountNumber;
    private Long balance;
}
