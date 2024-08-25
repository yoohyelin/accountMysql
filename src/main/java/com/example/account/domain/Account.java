package com.example.account.domain;

import com.example.account.exception.AccountException;
import com.example.account.type.AccountStatus;
import com.example.account.type.ErrorCode;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Account extends BaseEntity{

    @ManyToOne // 접근가능, 이 필드로 조건 걸떄는 엔티티 사용하기
    // @JoinColumn 어노테이션을 생략하면 "필드명_참조테이블의기본키"로 매핑
    private AccountUser accountUser;
    private String accountNumber; // 이게 왜 @Id가 아닌지????? // 왜 Long이 아닌지????

    @Enumerated(EnumType.STRING) // 엔티티의 enum , db저장 string으로
    private AccountStatus accountStatus;
    private Long balance; // 잔액

    private LocalDateTime registeredAt;
    private LocalDateTime unregisteredAt;

    public void useBalance(Long amount) { // 객체 안에서 직접 수행
        if(amount > balance) {
            throw new AccountException(ErrorCode.AMOUNT_EXCEED_BALANCE);
        }
        balance -= amount;
    }

    public void cancelBalance(Long amount) { // 객체 안에서 직접 수행
        if(amount < 0) {
            throw new AccountException(ErrorCode.INVALID_REQUEST);
        }
        balance += amount;
    }

}
