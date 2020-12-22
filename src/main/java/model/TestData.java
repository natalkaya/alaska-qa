package model;

public class TestData<D, R> {
    public D input;
    public R result;

    public TestData(D input, R result) {
        this.input = input;
        this.result = result;
    }

    @Override
    public String toString() {
        return "TestData{" +
                "input=" + input +
                ", result=" + result +
                '}';
    }
}
