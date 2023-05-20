package blog.web.board.service.impl;

import blog.domain.entity.*;
import blog.jwt.UserContextHolder;
//import blog.web.alert.repository.AlertRepository;
import blog.web.board.controller.dto.response.GetBoardByHashtagResponseDto;
import blog.web.hashtag.mapper.HashtagMapper;
import blog.web.hashtag.repository.HashtagRepository;
import blog.web.like.repository.LikeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import blog.web.board.controller.dto.request.DeleteBoardRequestDto;
import blog.web.board.controller.dto.request.UpdateBoardRequestDto;
import blog.web.board.controller.dto.response.*;
import blog.web.board.mapper.BoardMapper;
import blog.exception.ErrorCode;
import blog.web.user.repository.UserRepository;
import blog.utils.dto.ApiError;
import blog.web.board.controller.dto.request.CreateBoardRequestDto;
import blog.web.board.repository.BoardRepository;
import blog.web.board.service.BoardService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardServiceImpl implements BoardService {
//    private final AlertRepository alertRepository;

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final HashtagRepository hashtagRepository;
    private final BoardMapper mapper;
    private final HashtagMapper hashtagMapper;



    @Transactional
    @Override
    public CreateBoardResponseDto create(CreateBoardRequestDto createBoardRequestDto) {
        User findUser = findUser(UserContextHolder.getUserId());
        Board beforeBoard = mapper.toEntity(createBoardRequestDto);
        List<Hashtag> hashtagList = makeHashtagList(createBoardRequestDto.getHashtagList());
        beforeBoard.addUser(findUser);
        beforeBoard.addHashtag(hashtagList);
        Board afterBoard = boardRepository.save(beforeBoard);
        alertSave(afterBoard);

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
    public List<BoardOfTrackResponseDto> selectBoardOfTrack(String trackId, String order) {
        List<Board> boardList;
        List<BoardOfTrackResponseDto> boardOfTrackResponseDtoList = new ArrayList<>();
        if("byOrder".equals(order)) {
            boardList = boardRepository.findByStatusAndTrackIdOrderByBoardNoDesc(Status.Y, trackId);
            for(Board board : boardList) {
                BoardOfTrackResponseDto boardOfTrackResponseDto = mapper.toBoardOfTrackDto(board);
                boardOfTrackResponseDtoList.add(boardOfTrackResponseDto);
            }
        } else {
            boardList = likeRepository.countByBoardGroupByBoard();
            for(Board board : boardList) {
                BoardOfTrackResponseDto boardOfTrackResponseDto = mapper.toBoardOfTrackDto(board);
                boardOfTrackResponseDtoList.add(boardOfTrackResponseDto);
            }

        }
        return boardOfTrackResponseDtoList;

    }

    @Override
    public String likeBoard(Long boardNo) {
        Board findBoard = findBoard(boardNo);
        User findUser = findUser(UserContextHolder.getUserId());
        Like like = new Like().saveLike(findBoard, findUser);
        try {
            likeRepository.save(like);
            return "Like Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Like Failed";
        }
    }

    @Override
    @Transactional
    public String unLikeBoard(Long boardNo) {
        Board findBoard = findBoard(boardNo);
        User findUser = findUser(UserContextHolder.getUserId());
        Like beforeLike = findLike(findBoard, findUser);
        try {
            likeRepository.deleteByLikeNo(beforeLike.getLikeNo());
            return "UnLike Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "UnLike Failed";
        }
    }

    @Override
    public Long countLikes(Long boardNo) {
        Board findBoard = findBoard(boardNo);
        return likeRepository.countByBoard(findBoard);
    }

    @Override
    public Boolean isBoardLiked(Long boardNo) {
        User findUser = findUser(UserContextHolder.getUserId());
        Board findBoard = findBoard(boardNo);
        return likeRepository.existsByBoardAndUser(findBoard, findUser);
    }

    public List<GetBoardByHashtagResponseDto> getBoardByHashtag(String hashtagElement) {
        List<Board> boardList = findByHashtagName(hashtagElement).stream()
                .map(Hashtag::getBoard)
                .collect(Collectors.toList());
        List<GetBoardByHashtagResponseDto> getBoardByHashtagResponseDtoList = new ArrayList<>();
        for(Board board : boardList) {
            GetBoardByHashtagResponseDto getBoardByHashtagResponseDto = mapper.toBoardByHashtagDto(board);
            getBoardByHashtagResponseDtoList.add(getBoardByHashtagResponseDto);
        }
        return getBoardByHashtagResponseDtoList;
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

    private Like findLike(Board board, User user) {
        return likeRepository.findByBoardAndUser(board, user)
                .orElseThrow(() -> new ApiError(ErrorCode.LIKE_NOT_FOUND));
    }

    private List<Hashtag> findByHashtagName(String hashtag) {
        return hashtagRepository.findByName(hashtag)
                .orElseThrow(() -> new ApiError(ErrorCode.HASHTAG_NOT_FOUND));
    }


    private List<Hashtag> makeHashtagList(List<String> hashtagStrList) {
        List<Hashtag> hashtagList = new ArrayList<>();
        for(String hashtagStr : hashtagStrList) {
            Hashtag hashtag = hashtagMapper.toEntity(hashtagStr);
            hashtagList.add(hashtag);
        }
        return hashtagList;
    }

    private void alertSave(Board board) {
//        alertRepository.save(board)
    }



}
