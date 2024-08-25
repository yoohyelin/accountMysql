package com.example.account.exception;

import com.example.account.type.ErrorCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder // 빌드 왜 쓰는거지?
public class AccountException extends RuntimeException{
    private ErrorCode errorCode;
    private String errorMessage;

    // 사용자 정의 예외 클래스 이름은 Exception으로 끝나는 것이 좋고,
    // 메소드 선언을 포함할 수 있지만 대부분 생성자 선언만 포함한다.
    //  예외 발생 원인(예외 메시지)을 전달하기 위해 String 타입의 매개 변수를 갖는 생성자를 생성한다.
    public AccountException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    } // RuntimeException 상속받고 왜 안쓰는지?? 안쓰면 오류를 던질수가 있나?
}
