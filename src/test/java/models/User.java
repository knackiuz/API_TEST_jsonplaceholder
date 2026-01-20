package models;

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
    private int id;
}
