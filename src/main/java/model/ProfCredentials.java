package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
public class ProfCredentials {
    @JsonProperty("groupName")
    public String groupName;
    @JsonProperty("rights")
    public String rights;
}