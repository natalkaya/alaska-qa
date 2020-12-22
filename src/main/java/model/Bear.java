package model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JSON response example
 * {"bear_type":"BLACK","bear_name":"mikhail","bear_age":17.5}
 */
public class Bear {
    @JsonProperty("bear_type")
    private String type;
    @JsonProperty("bear_name")
    private String name;
    @JsonProperty("bear_age")
    private Double age;

    public Bear(String type, String name, Double age) {
        this.type = type;
        this.name = name;
        this.age = age;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public Bear(String type) {
        this.type = type;
    }

    public Bear(Double age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Double getAge() {
        return age;
    }

    public static Bear withDefaultParams() {
        return new Bear(BearTypes.BLACK.name(), StringsHelper.generateRandomName(5), StringsHelper.generateRandomAge(0.1, 60.0));
    }
}
