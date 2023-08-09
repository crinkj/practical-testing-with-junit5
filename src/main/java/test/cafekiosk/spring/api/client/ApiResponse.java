package test.cafekiosk.spring.api.client;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse<T> {

    private int code;
    private HttpStatus status;
    private String message;
    private T data;


    public ApiResponse(HttpStatus status, String message, T data) {
        this.code = status.value();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> of(HttpStatus httpstatus,String message, T data) {
        return new ApiResponse<>(httpstatus, message, data);
    }

    public static <T> ApiResponse<T> of(HttpStatus httpstatus, T data) {
        return of(httpstatus, httpstatus.name(), data);
    }

    public static <T> ApiResponse<T> ok(T data){
        return of(HttpStatus.OK, HttpStatus.OK.name(), data);
    }
}
