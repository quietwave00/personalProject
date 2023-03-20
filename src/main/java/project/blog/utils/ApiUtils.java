package project.blog.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import project.blog.user.exception.ErrorCode;
import project.blog.utils.dto.ApiError;
import project.blog.utils.dto.ApiResult;

@Getter
@Setter
@RequiredArgsConstructor
public class ApiUtils<T> {
    public static <T> ApiResult<T> success(T response) {
        return new ApiResult<>(true, response);
    }

    public ApiResult<?> error(ErrorCode errorCode) {
        return new ApiResult<>(false, new ApiError(errorCode));
    }




}
