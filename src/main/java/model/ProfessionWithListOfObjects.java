package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@ToString
@EqualsAndHashCode
@Getter
public class ProfessionWithListOfObjects {
    @JsonProperty("profession")
    public String name;
    @JsonProperty("credentials")
    public List<Object> credentials;
}
