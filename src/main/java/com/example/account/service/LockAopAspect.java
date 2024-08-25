package com.example.account.service;

import com.example.account.aop.AccountLockIdInterface;
import com.example.account.dto.CancelBalance;
import com.example.account.dto.UseBalance;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // aop 사용
@Component
@Slf4j
@RequiredArgsConstructor
public class LockAopAspect { // aop를 사용하여 메소드마다 개별적으로 락을 관리하지 않고, 애노테이션만으로 잠금을 자동으로 적용할 수 있습니다.
    // aop를 사용하여 특정 메소드에 자동으로 lock 적용,적용방법은 애노테이션 붙여서
    // AOP를 사용함으로써 메소드의 핵심 로직과 락 관리 로직을 분리할 수 있습니다.
    // 이는 메소드가 락을 필요로 할 때, 애노테이션만으로 간편하게 락을 적용할 수 있게 해줍니다.
    // LockAopAspect는 다양한 메소드에 재사용할 수 있으며,
    // LockService는 락 관리의 핵심 로직을 제공하는 재사용 가능한 서비스입니다.
    // LockService**는 락을 획득하고 해제하는 구체적인 로직을 처리하는 서비스입니다.
    // 이 서비스는 락의 구현 세부 사항을 관리합니다.
    //**LockAopAspect**는 AOP를 통해 메소드에 대한 락을 자동으로 적용하는 역할을 합니다.
    // 이로 인해 비즈니스 로직과 락 로직을 분리하고, 코드의 재사용성과 유지 보수성을 향상시킵니다.
    private final LockService lockService;

    @Around("@annotation(com.example.account.aop.AccountLock) && args(request)")
    public Object aroundMethod(
            ProceedingJoinPoint pjp,
            AccountLockIdInterface request
    ) throws Throwable {
        // lock 취득 시도
        lockService.Lock(request.getAccountNumber());
        try{
            return pjp.proceed();
        }finally {
            // lock 해제
            lockService.unlock(request.getAccountNumber());

        }
    }
}
