package model.reponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bear {
    @JsonProperty("bear_id")
    private Integer id;
    @JsonProperty("bear_type")
    private String type;
    @JsonProperty("bear_name")
    private String name;
    @JsonProperty("bear_age")
    private Double age;

    public Bear(Integer id, String type, String name, Double age) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.age = age;
    }

    public Bear(String type, String name, Double age) {
        this.type = type;
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
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

    public static Bear fromBearRequest(Integer bearId, model.Bear bearRequest) {
        return new Bear(bearId, bearRequest.getType(), bearRequest.getName(), bearRequest.getAge());
    }

    public static Bear fromBearModel(model.Bear bearRequest) {
        return new Bear(bearRequest.getType(), bearRequest.getName(), bearRequest.getAge());
    }
}
