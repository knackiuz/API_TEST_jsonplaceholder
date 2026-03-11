package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Builder
@Data
@Jacksonized
public class User {
    private String name;
    private String gender;
    private String email;
    private String status;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int id;
}
