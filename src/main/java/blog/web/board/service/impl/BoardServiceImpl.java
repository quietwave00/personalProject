package blog.web.board.service.impl;

import blog.domain.entity.Hashtag;
import blog.domain.entity.Status;
import blog.jwt.UserContextHolder;
import blog.web.hashtag.mapper.HashtagMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import blog.web.board.controller.dto.request.DeleteBoardRequestDto;
import blog.web.board.controller.dto.request.UpdateBoardRequestDto;
import blog.web.board.controller.dto.response.*;
import blog.web.board.mapper.BoardMapper;
import blog.domain.entity.Board;
import blog.domain.entity.User;
import blog.exception.ErrorCode;
import blog.web.user.repository.UserRepository;
import blog.utils.dto.ApiError;
import blog.web.board.controller.dto.request.CreateBoardRequestDto;
import blog.web.board.repository.BoardRepository;
import blog.web.board.service.BoardService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final BoardMapper mapper;
    private final HashtagMapper hashtagMapper;



    @Override
    public CreateBoardResponseDto create(CreateBoardRequestDto createBoardRequestDto) {
        User findUser = findUser(UserContextHolder.getUserId());
        Board beforeBoard = mapper.toEntity(createBoardRequestDto);
        List<Hashtag> hashtagList = makeHashtagList(createBoardRequestDto.getHashtagList());
        beforeBoard.addUser(findUser);
        beforeBoard.addHashtag(hashtagList);
        Board afterBoard = boardRepository.save(beforeBoard);
        return mapper.toCreateDto(afterBoard);
    }

    @Override
    public UpdateBoardResponseDto update(UpdateBoardRequestDto updateBoardRequestDto) {
        Board findBoard = findBoard(updateBoardRequestDto.getBoardNo());
        findBoard.update(updateBoardRequestDto.getContent());
        Board updateBoard = boardRepository.save(findBoard);
        return mapper.toUpdateDto(updateBoard);
    }

    @Override
    public DetailsBoardResponseDto detail(Long boardNo) {
        Board findBoard = findBoard(boardNo);
        //조회수
        if(!UserContextHolder.getUserId().equals(findBoard.getUser().getUserId())) {
            findBoard.updateCount();
            boardRepository.save(findBoard);
        }
        return mapper.toDetailsDto(findBoard);
    }

    @Override
    public DeleteBoardResponseDto delete(DeleteBoardRequestDto deleteBoardRequestDto) {
        Board findBoard = findBoard(deleteBoardRequestDto.getBoardNo());
        findBoard.deleteBoard();
        boardRepository.save(findBoard);
        return mapper.toDeleteDto(findBoard);
    }

    @Override
    public Result<?> selectAll() {
        List<Board> boardList = boardRepository.findByStatusOrderByBoardNoDesc(Status.Y);
        List<BoardListResponseDto> boardListResponseDtoList = new ArrayList<>();
        for (Board board : boardList) {
            BoardListResponseDto boardListResponseDto = mapper.toBoardListDto(board);
            boardListResponseDtoList.add(boardListResponseDto);
        }
        return new Result<>(boardListResponseDtoList);
    }

    @Override
    public List<BoardOfTrackResponseDto> selectBoardOfTrack(String trackId) {
        List<Board> boardList = boardRepository.findByStatusAndTrackIdOrderByBoardNoDesc(Status.Y, trackId);
        List<BoardOfTrackResponseDto> boardOfTrackResponseDtoList = new ArrayList<>();
        for(Board board : boardList) {
            BoardOfTrackResponseDto boardOfTrackResponseDto = mapper.toBoardOfTrackDto(board);
            boardOfTrackResponseDtoList.add(boardOfTrackResponseDto);
        }
        return boardOfTrackResponseDtoList;
    }


    //단일 메소드
    private Board findBoard(Long boardNo) {
        return boardRepository.findByBoardNo(boardNo)
                .orElseThrow(() -> new ApiError(ErrorCode.BOARD_NOT_FOUND));
    }

    private User findUser(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new ApiError(ErrorCode.USER_ID_NOT_FOUND));
    }


    private List<Hashtag> makeHashtagList(List<String> hashtagStrList) {
        List<Hashtag> hashtagList = new ArrayList<>();
        for(String hashtagStr : hashtagStrList) {
            Hashtag hashtag = hashtagMapper.toEntity(hashtagStr);
            hashtagList.add(hashtag);
        }
        return hashtagList;
    }

}
