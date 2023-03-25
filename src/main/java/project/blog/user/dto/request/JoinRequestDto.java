package project.blog.user.dto.request;

import lombok.Getter;
import lombok.Setter;
import project.blog.domain.entity.Role;
import project.blog.domain.entity.User;

@Getter
@Setter
public class JoinRequestDto {

    private String userId;

    private String nickname;

    private String password;


}
