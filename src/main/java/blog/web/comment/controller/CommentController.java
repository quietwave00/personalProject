package blog.web.comment.controller;

import blog.utils.ApiUtils;
import blog.utils.dto.ApiResult;
import blog.web.comment.controller.dto.request.CreateCommentRequestDto;
import blog.web.comment.controller.dto.request.DeleteCommentRequestDto;
import blog.web.comment.controller.dto.request.UpdateCommentRequestDto;
import blog.web.comment.controller.dto.response.CreateCommentResponseDto;
import blog.web.comment.controller.dto.response.DeleteCommentResponseDto;
import blog.web.comment.controller.dto.response.UpdateCommentResponseDto;
import blog.web.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


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

    //댓글 수정
    @PatchMapping("/comments")
    public ApiResult<UpdateCommentResponseDto> update(@RequestBody UpdateCommentRequestDto updateCommentRequestDto) {
        UpdateCommentResponseDto updateCommentResponseDto = commentService.update(updateCommentRequestDto);
        return ApiUtils.success(updateCommentResponseDto);
    }

    //댓글 삭제
    @PatchMapping("/comments/status")
    public ApiResult<DeleteCommentResponseDto> delete(@RequestBody DeleteCommentRequestDto deleteCommentRequestDto) {
        DeleteCommentResponseDto deleteCommentResponseDto = commentService.delete(deleteCommentRequestDto);
        return ApiUtils.success(deleteCommentResponseDto);
    }

}
