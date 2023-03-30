package blog.web.user.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinRequestDto {

    private String userId;

    private String nickname;

    private String password;


}
