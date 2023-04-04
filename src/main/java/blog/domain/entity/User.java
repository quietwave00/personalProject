package blog.domain.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import blog.web.user.dto.request.JoinRequestDto;
import blog.web.user.dto.response.JoinResponseDto;
import blog.web.user.dto.response.LoginResponseDto;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

    @NotBlank
    private String nickname;

    @CreationTimestamp
    private Timestamp createdDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Board> board = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comment = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Status status;

    //--연관관계 메소드--
    public void addBoard(Board board) {
        this.board.add(board);
        board.setUser(this);
    }

    //--toDto--
    public static JoinResponseDto toJoinDto(User user) {
        return JoinResponseDto.builder()
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .build();
    }

    public static LoginResponseDto toLoginDto(User user) {
        return LoginResponseDto.builder()
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .build();
    }




    //--비즈니스 로직--
    //가입
    public static User createUser(JoinRequestDto joinRequestDto) {
        return User.builder()
                .userId(joinRequestDto.getUserId())
                .password(joinRequestDto.getPassword())
                .nickname(joinRequestDto.getNickname())
                .role(Role.ROLE_PUBLIC_USER)
                .status(Status.Y)
                .build();
    }



}
