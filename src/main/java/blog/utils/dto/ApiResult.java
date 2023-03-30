package blog.utils.dto;

import lombok.*;

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
