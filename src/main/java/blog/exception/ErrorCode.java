package blog.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    USER_ID_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 아이디가 없습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),
    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "이미 삭제되었거나 없는 게시글입니다.")

    ;

    private HttpStatus httpStatus;
    private String message;
}
