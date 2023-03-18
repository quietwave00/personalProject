package project.blog.utils.dto;

import lombok.*;
import project.blog.user.exception.ErrorCode;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ApiError extends RuntimeException{

    private ErrorCode errorCode;


}
