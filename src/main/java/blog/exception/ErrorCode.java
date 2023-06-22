package blog.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    USER_ID_NOT_FOUND(HttpStatus.NOT_FOUND, "일치하는 아이디가 없습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),
    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "이미 삭제되었거나 없는 게시글입니다."),
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "이미 삭제되었거나 없는 댓글입니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    LIKE_NOT_FOUND(HttpStatus.NOT_FOUND, "좋아요 내역을 찾을 수 없습니다."),
    HASHTAG_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 해쉬태그를 찾을 수 없습니다."),
    FILE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 파일을 찾을 수 없습니다.")
    ;

    private HttpStatus httpStatus;
    private String message;
}
