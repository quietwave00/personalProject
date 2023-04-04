package blog.web.comment.controller;

import blog.utils.ApiUtils;
import blog.utils.dto.ApiResult;
import blog.web.comment.controller.dto.request.CreateCommentRequestDto;
import blog.web.comment.controller.dto.response.CreateCommentResponseDto;
import blog.web.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/blog/user")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //댓글 작성
    @PostMapping("/comments")
    public ApiResult<CreateCommentResponseDto> create(@RequestBody CreateCommentRequestDto createCommentRequestDto) {
        CreateCommentResponseDto createCommentResponseDto = commentService.create(createCommentRequestDto);
        return ApiUtils.success(createCommentResponseDto);
    }

}
