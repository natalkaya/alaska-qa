package model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.reponse.Bear;

import java.util.Optional;

public class ExpectedResponse {
    private Integer returnedCode;
    private Optional<String> message;

    private static ObjectMapper objectMapper = new ObjectMapper();

    public Integer getReturnedCode() {
        return returnedCode;
    }

    public Optional<String> getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ExpectedResponse{" +
                "returnedCode=" + returnedCode +
                ", message=" + message +
                '}';
    }

    public ExpectedResponse(Integer returnedCode, Optional<String> message) {
        this.returnedCode = returnedCode;
        this.message = message;
    }

    public static ExpectedResponse fromResponseTypes(ResponseType responseType) {
        return new ExpectedResponse(responseType.getCode(), responseType.getMessage());
    }

    public static ExpectedResponse getBy(Integer returnedCode, Bear bearResponse) throws JsonProcessingException {
        return new ExpectedResponse(returnedCode, Optional.of(objectMapper.writeValueAsString(bearResponse)));
    }


}
