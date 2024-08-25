package com.example.account.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

// Account, AccountUser 공통인 부분 작성
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName= "doesNotUseThisBuilder")
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // 생성되었을떄, 업데이트되었을때 시간 자동으로 담아주기 위해
public class BaseEntity {
    @Id
    @GeneratedValue
    private Long id; // Account에서는 id가 중복될 수 있지않나????

    @CreatedDate
    private LocalDateTime createAt;
    @LastModifiedDate
    private LocalDateTime updateAt;
}
