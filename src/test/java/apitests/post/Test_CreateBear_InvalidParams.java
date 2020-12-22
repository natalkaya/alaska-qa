package apitests.post;

import apitests.ApiTestBase;
import model.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Test_CreateBear_InvalidParams extends ApiTestBase {

    @ParameterizedTest
    @MethodSource("notValidParamsCreatedBearsData")
    void createBear(TestData<Bear, ResponseType> testData) {
        alaskaService.createBear(testData.input)
                .then()
                .statusCode(testData.result.getCode());
    }

    private static Stream<Arguments> notValidParamsCreatedBearsData() {
        return Stream.of(
                arguments(new TestData<>(new Bear("NotValidBearType", StringsHelper.generateRandomName(256),
                        StringsHelper.generateRandomAge(1.0, 30.0)), ResponseType.ERROR)),
                arguments(new TestData<>(new Bear(BearTypes.BLACK.name(), StringsHelper.generateRandomName(0),
                        StringsHelper.generateRandomAge(1.0, 30.0)), ResponseType.ERROR)),
                arguments(new TestData<>(new Bear(BearTypes.BLACK.name(), StringsHelper.generateRandomName(10),
                        StringsHelper.generateRandomAge(-2.0, -1.0)), ResponseType.ERROR)),
                arguments(new TestData<>(new Bear(BearTypes.BLACK.name(), StringsHelper.generateRandomName(10),
                        0.0), ResponseType.ERROR)),
                arguments(new TestData<>(new Bear(StringsHelper.generateRandomAge(0.1, 99.99)), ResponseType.ERROR)),
                arguments(new TestData<>(new Bear(BearTypes.BLACK.name()), ResponseType.ERROR))
        );
    }
}
