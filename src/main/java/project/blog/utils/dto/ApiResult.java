package project.blog.utils.dto;

import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResult<T> {
    private boolean success;
    private T response;
    private ApiError apiError;

    public ApiResult(boolean success, T response) {
        this.success = success;
        this.response = response;
    }

    public ApiResult(boolean success, ApiError apiError) {
        this.success = success;
        this.apiError = apiError;
    }



}
