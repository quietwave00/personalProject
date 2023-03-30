package blog.utils;

import blog.utils.dto.ApiError;
import blog.utils.dto.ApiResult;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import blog.web.user.exception.ErrorCode;

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
