package blog.web.board.controller.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Result<T> {
    private T data;
    public Result(T data) {
        this.data = data;
    }
}
