package pojo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
  @JsonProperty("course")
  String course;
  @JsonProperty("email")
  String email;
  @JsonProperty("age")
  int age;
  @JsonProperty("name")
  private String name;
}
