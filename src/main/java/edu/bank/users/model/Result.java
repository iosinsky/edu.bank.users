package edu.bank.users.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T>  {

    private int code;
    private Error error;
    private T data;

    public Result(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public Result(int code, Error error) {
        this.code = code;
        this.error = error;
    }

    public Result(int code, String errorMessage) {
        this.code = code;
        this.error = new Error(errorMessage, null);
    }

    public static <T> Result<T> success(final T data) {
        return new Result<T>(0,  data);
    }

    public static <T> Result<T> error(String errorMessage, Exception ex) {
        Error error = new Error();
        error.setText(errorMessage);
        String profile = System.getProperty("spring.profiles.active", "production");
        if ("dev".equalsIgnoreCase(profile) || "local".equalsIgnoreCase(profile)) {
            if (ex != null) {
                List<String> trace = Arrays.stream(ex.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.toList());
                error.setTrace(trace.toArray(new String[trace.size()]));
            }
        }

        return new Result<>(-1, error);
    }

    public static <T> Result<T> error(String errorMessage) {
        return new Result<>(-1, errorMessage);
    }

    public static <T> Result<T> error(int errorCode, String errorMessage) {
        return new Result<>(errorCode, errorMessage);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Error {
        private String text;
        private String[] trace;
    }

}
