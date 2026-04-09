package ExampleDTO.api;

import ExampleDTO.domain.Role;
import ExampleDTO.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;
/*
@Component
class Mapper {
    public UserDTO toDto(User user) {
        String name = user.getName();

        List<String> roles = user
                .getRoles()
                .stream()
                .map(Role::getName)
                .collect(toList());

//        roles = new LinkedList<>();
//        for(Role x: user.getRoles()){
//            roles.add(x.getName());
//        }


        return new UserDTO(name, roles);
    }

    public User toUser(UserCreationDTO userDTO) {
        return new User(userDTO.getName(), userDTO.getPassword(), new ArrayList<>());
    }
}*/

// Per utilizzare il mapper manuale, commentare il mapper qui sotto e decommentare quello sopra
@Mapper(componentModel = "spring")
interface UserMapper {
    @Mapping(target = "roles", source = "roles")
    UserDTO toDto(User user);
    User toUser(UserCreationDTO userDTO);

    default List<Role> toRole(List<String> roles){
        return roles.stream().map(Role::new).collect(toList());
    }

    default List<String> toRoleName(List<Role> roles){
        return roles.stream().map(Role::getName).collect(toList());
    }
}