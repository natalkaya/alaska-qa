package model;

import java.util.Optional;

import static java.net.HttpURLConnection.HTTP_INTERNAL_ERROR;
import static java.net.HttpURLConnection.HTTP_OK;

public enum ResponseType {
    OK(HTTP_OK, Optional.empty()),
    ERROR(HTTP_INTERNAL_ERROR, Optional.empty()),
    EMTY(HTTP_OK, Optional.of("EMTY"));

    private Integer code;
    private Optional<String> message;

    ResponseType(Integer code, Optional<String> message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public Optional<String> getMessage() {
        return message;
    }
}
