package apitests.get;

import apitests.ApiTestBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import model.Bear;
import model.ExpectedResponse;
import model.ResponseType;
import model.TestData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.net.HttpURLConnection;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Test_GetRequests extends ApiTestBase {
    private static Stream<Arguments> inputData_getRequest() throws JsonProcessingException {
        final Bear bear = Bear.withDefaultParams();
        final Integer returnBearId = alaskaService.createAndReturnBearId(bear);
        return Stream.of(
                arguments(new TestData<>(returnBearId,
                        ExpectedResponse.getBy(HttpURLConnection.HTTP_OK, model.reponse.Bear.fromBearRequest(returnBearId, bear)))),
                arguments(new TestData<>(0, ExpectedResponse.fromResponseTypes(ResponseType.EMTY)))
        );
    }

    @ParameterizedTest
    @MethodSource("inputData_getRequest")
    public void checkByDiffCases(TestData<Integer, ExpectedResponse> testData) {
        final Response response = alaskaService.getBear(testData.input.toString());
        response.then().statusCode(testData.result.getReturnedCode());
        response.htmlPath().get("html.body").equals(testData.result.getMessage());
    }


}
