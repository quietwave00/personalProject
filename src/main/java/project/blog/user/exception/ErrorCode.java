package project.blog.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    USER_ID_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 아이디가 없습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");

    private HttpStatus httpStatus;
    private String message;
}
