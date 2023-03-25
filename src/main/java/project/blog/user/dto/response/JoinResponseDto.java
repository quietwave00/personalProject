package project.blog.user.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JoinResponseDto {
    private String userId;
    private String nickname;
}
