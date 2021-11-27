package kz.yesserzhanov.sobes.db.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class City {
    private long id;
    @JsonProperty("name_ru")
    private String nameRu;
    @JsonProperty("name_kz")
    private String nameKz;
    @JsonProperty("state_id")
    private long stateId;
}
