package blog.utils.dto;

import lombok.*;
import blog.exception.ErrorCode;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiError extends RuntimeException{

    private ErrorCode errorCode;


}
