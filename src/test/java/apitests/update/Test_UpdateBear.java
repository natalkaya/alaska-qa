package apitests.update;

import apitests.ApiTestBase;
import io.restassured.http.ContentType;
import model.Bear;
import model.StringsHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static java.net.HttpURLConnection.HTTP_OK;

public class Test_UpdateBear extends ApiTestBase {

    @Test
    public void updateWhenValidParams() {
        final Bear withDefaultParams = Bear.withDefaultParams();
        final Bear updateWithDefaultParams = new Bear(withDefaultParams.getType(), withDefaultParams.getName(), withDefaultParams.getAge());
        updateWithDefaultParams.setName("UPDATED_" + StringsHelper.generateRandomName(4));

        Integer bearId = alaskaService.createAndReturnBearId(withDefaultParams);
        alaskaService.updateBear(bearId.toString(), updateWithDefaultParams);
        model.reponse.Bear actualRes = alaskaService.getBear(bearId.toString())
                .then()
                .statusCode(HTTP_OK)
                .contentType(ContentType.JSON)
                .extract()
                .as(model.reponse.Bear.class);
        Assertions.assertEquals(model.reponse.Bear.fromBearRequest(bearId, updateWithDefaultParams), actualRes);
    }

    @Test
    public void noUpdateWhenNotValidParams() {
        // todo:
        //  here should add tests that check valid bear params in request:
        //   - test result should be 400, error message describes a problem param
        //   - Get request by failed try to updating bear should return not updated value
    }
}
