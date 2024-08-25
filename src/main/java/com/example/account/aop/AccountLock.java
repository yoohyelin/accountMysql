package com.example.account.aop;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface AccountLock { // aop를 통해 메소드에 lock 기능을 추가하는 애노테이션
    long tryLockTime() default 5000L; // 락을 획득하려고 시도할 최대 시간
}
