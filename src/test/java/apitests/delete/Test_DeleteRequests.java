package apitests.delete;

import apitests.ApiTestBase;
import model.reponse.Bear;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.net.HttpURLConnection.HTTP_OK;

public class Test_DeleteRequests extends ApiTestBase {

    public static void checkCreatedBear(Integer bearId) {
        Assertions.assertTrue(bearId > 0, "BearId is not valid");
        alaskaService.getBear(bearId.toString()).then().statusCode(HTTP_OK);
    }

    @AfterAll
    static void suiteTeardown() {
        removeBears();
    }

    @Test
    public void deleteBear() {
        Integer createdBearId = alaskaService.createAndReturnBearId(model.Bear.withDefaultParams());
        Assertions.assertTrue(createdBearId > 0, "BearId is not valid");
        alaskaService.deleteBear(createdBearId.toString())
                .then().statusCode(HTTP_OK);
        alaskaService.getBear(createdBearId.toString()).htmlPath().getString("html.body").equals("EMPTY");
        Assertions.assertTrue(
                Arrays.stream(alaskaService.getBears().htmlPath().getObject("html.body", Bear[].class))
                        .noneMatch(bear -> bear.getId().equals(createdBearId))
        );
    }

    @Test
    public void deleteAllBears() {
        IntStream.range(0, 10)
                .mapToObj(num -> {
                    log.info(String.format("Creating %s bear", num));
                    return model.Bear.withDefaultParams();
                })
                .map(bear -> alaskaService.createAndReturnBearId(bear))
                .peek(Test_DeleteRequests::checkCreatedBear)
                .collect(Collectors.toList())
                .forEach(id -> {
                    log.info(String.format("Deleting bear with id = %s", id));
                    alaskaService.deleteBear(id.toString()).then().statusCode(HTTP_OK);
                });
    }
}
