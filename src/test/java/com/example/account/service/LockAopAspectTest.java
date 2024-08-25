package com.example.account.service;

import com.example.account.dto.UseBalance;
import com.example.account.exception.AccountException;
import com.example.account.type.ErrorCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.example.account.type.ErrorCode.ACCOUNT_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LockAopAspectTest {
    @Mock
    private LockService lockService;

    @Mock
    private ProceedingJoinPoint proceedingJoinPoint;

    @InjectMocks
    private LockAopAspect lockAopAspect;

    @Test
    void lockAndUnlock() throws Throwable {
    // given
        ArgumentCaptor<String> lockArgumentCapor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> unLockArgumentCapor = ArgumentCaptor.forClass(String.class);
        UseBalance.Request request =
                new UseBalance.Request(123L,"1234",100L);
    // when
        lockAopAspect.aroundMethod(proceedingJoinPoint,request);
    // then
        verify(lockService,times(1))
                .Lock(lockArgumentCapor.capture());
        verify(lockService,times(1))
                .unlock(unLockArgumentCapor.capture());
        assertEquals("1234",lockArgumentCapor.getValue());
        assertEquals("1234",unLockArgumentCapor.getValue());

    }

    @Test
    void lockAndUnlock_evenIfThrow() throws Throwable {
        // given
        ArgumentCaptor<String> lockArgumentCapor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> unLockArgumentCapor = ArgumentCaptor.forClass(String.class);
        UseBalance.Request request =
                new UseBalance.Request(123L,"54321",100L);

        given(proceedingJoinPoint.proceed())
                .willThrow(new AccountException(ACCOUNT_NOT_FOUND));
        // when
        assertThrows(AccountException.class, ()->
        lockAopAspect.aroundMethod(proceedingJoinPoint,request));
        // then
        verify(lockService,times(1))
                .Lock(lockArgumentCapor.capture());
        verify(lockService,times(1))
                .unlock(unLockArgumentCapor.capture());
        assertEquals("54321",lockArgumentCapor.getValue());
        assertEquals("54321",unLockArgumentCapor.getValue());

    }
}