package httpclient;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.Bear;

/**
 * POST /bear - create
 * GET /bear - read all bears
 * GET /bear/:id - read specific bear
 * PUT /bear/:id - update specific bear
 * DELETE /bear - delete all bears
 * DELETE /bear/:id - delete specific bear
 */
public class AlaskaHttpClient {
    private final RequestSpecification requestSpecification;
    private final String baseUrl;

    private final static String BASE_PATH = "/";
    private final static String BEARS = "/bear";
    private final static String BEAR_ID = "/bear/%s";

    private String bearIdUrl(String bearId) {
        return String.format(BEAR_ID, bearId);
    }

    public AlaskaHttpClient(String baseUrl) {
        this.baseUrl = baseUrl;
        this.requestSpecification = new RequestSpecBuilder()
                .log(LogDetail.ALL)
                .setBaseUri(baseUrl)
                .setBasePath(BASE_PATH)
                .setContentType(ContentType.JSON)
                .build();
    }

    private RequestSpecification given() {
        return RestAssured.given(requestSpecification);
    }

    public Response createBear(Bear bear) {
        return given().body(bear).post(BEARS);
    }

    public Integer createAndReturnBearId(Bear bear) {
        return createBear(bear).htmlPath().getInt("html.body");
    }

    public Response getBears() {
        return given().contentType(ContentType.HTML).get(BEARS);
    }

    public Response getBear(String bearId) {
        return given().get(bearIdUrl(bearId));
    }

    public Response updateBear(String bearId, Bear bear) {
        return given().body(bear).put(bearIdUrl(bearId));
    }

    public Response deleteAllBears() {
        return given().delete(BEARS);
    }

    public Response deleteBear(String bearId) {
        return given().delete(bearIdUrl(bearId));
    }
}
