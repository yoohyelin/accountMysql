package com.example.account.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

// 엔티티 클래스들로 구성
// 엔티티 클래스는 데이터베이스 테이블과 매핑
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity //JPA가 엔티티로 인식하며 테이블과 링크
@EntityListeners(AuditingEntityListener.class)
public class AccountUser extends BaseEntity{

    private String name;

}
