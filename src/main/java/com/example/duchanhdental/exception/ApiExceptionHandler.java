package com.example.duchanhdental.exception;

import com.example.duchanhdental.model.response.ErrorMessage;
import com.google.api.gax.rpc.NotFoundException;
import com.google.rpc.BadRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.MethodNotAllowedException;

@RestControllerAdvice
public class ApiExceptionHandler {


    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleAllException(Exception ex, WebRequest request) {
        return new ErrorMessage(500, ex.getLocalizedMessage());
    }


    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage notFoundException(Exception ex, WebRequest request) {
        return new ErrorMessage(404, "Đối tượng không tìm thấy");
    }

    @ExceptionHandler(HttpServerErrorException.BadGateway.class)
    @ResponseStatus(value = HttpStatus.BAD_GATEWAY)
    public ErrorMessage badGateWayException(Exception ex, WebRequest request) {
        return new ErrorMessage(502, "Phản hồi không hợp lệ từ phía server");
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorMessage methodNotAllowException(Exception ex, WebRequest request) {
        return new ErrorMessage(405, "Phương thức không được cho phép");
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage TodoException(Exception ex, WebRequest request) {
        return new ErrorMessage(400, "Đối tượng không tồn tại");
    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorMessage forbiddenException(Exception ex, WebRequest request) {
        return new ErrorMessage(403, "Máy chủ từ chối cung cấp tài nguyên được yêu cầu");
    }

    @ExceptionHandler(HttpClientErrorException.Conflict.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ErrorMessage conflictException(Exception ex, WebRequest request) {
        return new ErrorMessage(409, "Yêu cầu xung đột với trạng thái hiện tại của máy chủ");
    }

    @ExceptionHandler(HttpClientErrorException.TooManyRequests.class)
    @ResponseStatus(value = HttpStatus.TOO_MANY_REQUESTS)
    public ErrorMessage tooManyRequestsException(Exception ex, WebRequest request) {
        return new ErrorMessage(429, "Quá nhiều request gửi đến máy chủ cùng lúc");
    }

    @ExceptionHandler(HttpServerErrorException.GatewayTimeout.class)
    @ResponseStatus(value = HttpStatus.GATEWAY_TIMEOUT)
    public ErrorMessage gatewayTimeoutException(Exception ex, WebRequest request) {
        return new ErrorMessage(504, "Máy chủ không thể nhận được phản hồi kịp thời.");
    }
}
