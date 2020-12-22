package apitests.post;

import apitests.ApiTestBase;
import model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class Test_CreateBear_ValidParams extends ApiTestBase {
    @ParameterizedTest
    @MethodSource("successfulCreatedBearsData")
    void createBear(TestData<Bear, ResponseType> testData) {
        Assertions.assertTrue(alaskaService.createAndReturnBearId(testData.input) > 0,
                "Created id is not valid");
    }

    static Stream<Arguments> successfulCreatedBearsData() {
        return Stream.of(
                arguments(new TestData<>(new Bear(BearTypes.BLACK.name(), StringsHelper.generateRandomName(256),
                        StringsHelper.generateRandomAge(1.0, 30.0)), ResponseType.OK)),
                arguments(new TestData<>(new Bear(BearTypes.WHITE.name(), StringsHelper.generateRandomName(256),
                        StringsHelper.generateRandomAge(0.0, 60.0)), ResponseType.OK))
        );
    }
}
