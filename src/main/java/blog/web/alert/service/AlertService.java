package blog.web.alert.service;

import blog.domain.entity.Alert;
import blog.domain.entity.User;
import blog.exception.ErrorCode;
import blog.jwt.UserContextHolder;
import blog.utils.dto.ApiError;
import blog.web.alert.controller.dto.response.AlertResponseDto;
import blog.web.alert.mapper.AlertMapper;
import blog.web.alert.repository.AlertRepository;
import blog.web.user.repository.UserRepository;
import com.mysql.cj.xdevapi.JsonParser;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.bson.json.JsonObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlertService {

    private final AlertRepository alertRepository;
    private final UserRepository userRepository;

    private final AlertMapper alertMapper;

    public AlertResponseDto getAlert() {
        User findUser = findUser(UserContextHolder.getUserId());
        Document findAlert = alertRepository.findByNickname(findUser.getNickname());
        return getAlertDto(findAlert);
    }

    //단일 메소드
    private User findUser(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new ApiError(ErrorCode.USER_ID_NOT_FOUND));
    }

    private AlertResponseDto getAlertDto(Document findAlert) {
        String id = findAlert.getObjectId("_id").toHexString();
        String nickname = findAlert.getString("nickname");
        String title = findAlert.getString("title");
        String alertCategory = findAlert.getString("alertCategory");

        return alertMapper.toResponseDto(id, nickname, title, alertCategory);
    }
}
