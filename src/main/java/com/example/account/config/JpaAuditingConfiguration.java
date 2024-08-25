package com.example.account.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing //Auditing을 활성화
public class JpaAuditingConfiguration { // Jpa Config 설정, 테스트 시 문제 생길 수 있어서 분리
}
