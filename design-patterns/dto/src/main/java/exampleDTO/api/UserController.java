package exampleDTO.api;

import exampleDTO.domain.RoleService;
import exampleDTO.domain.User;
import exampleDTO.domain.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/users")
class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final UserMapper mapper;

    public UserController(UserService userService,  RoleService roleService, UserMapper mapper) {
        this.userService = userService;
        this.roleService = roleService;
        this.mapper = mapper;
    }

    @GetMapping
    @ResponseBody
    public List<UserDTO> getUsers() {
        return userService.getAll()
            .stream()
            .map(mapper::toDto)
            .collect(toList());
    }


    @PostMapping
    @ResponseBody
    public UserIdDTO create(@RequestBody UserCreationDTO userDTO) {
        User user = mapper.toUser(userDTO);

        /*
        Commentato perchè usiamo il Mapping di map-struct di Spring (dopo le modifiche in aula)
        Decommentare se si vuole utilizzare il mapper "manuale"

        userDTO.getRoles()
                .stream()
                .map(roleService::getOrCreate)
                .forEach(user::addRole);
        */

        userService.save(user);

        return new UserIdDTO(user.getId());
    }

}
