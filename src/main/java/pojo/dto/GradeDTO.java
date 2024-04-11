package pojo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

// используем ломбок как альтернативу, чтобы не прописывать сеттеры и геттеры
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GradeDTO {
  @JsonProperty("name")
  String name;
  @JsonProperty("score")
  int score;
}
