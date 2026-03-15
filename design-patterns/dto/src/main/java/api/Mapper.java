package api;

import domain.Role;
import domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
class Mapper {
    public UserDTO toDto(User user) {
        String name = user.getName();

        List<String> roles = user
                .getRoles()
                .stream()
                .map(x -> x.getName())
                .collect(toList());

        roles = new LinkedList<>();
        for(Role x: user.getRoles()){
            roles.add(x.getName());
        }


        return new UserDTO(name, roles);
    }

    public User toUser(UserCreationDTO userDTO) {
        return new User(userDTO.getName(), userDTO.getPassword(), new ArrayList<>());
    }
}
