package exampleDTO.api;

import java.util.List;

public class UserDTO {
    private final String name;
    private final List<String> roles;

    public UserDTO(String name, List<String> roles) {
        this.name = name;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public List<String> getRoles() {
        return roles;
    }

}
