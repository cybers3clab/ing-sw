package ExampleDTO.api;

import ExampleDTO.domain.RoleService;
import ExampleDTO.domain.User;
import ExampleDTO.domain.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/users")
class UserController {

    private final UserService userService;
    private final RoleService roleService;
    private final Mapper mapper;

    public UserController(UserService userService,  RoleService roleService, Mapper mapper) {
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

        userDTO.getRoles()
                .stream()
                .map(roleService::getOrCreate)
                .forEach(user::addRole);

        userService.save(user);

        return new UserIdDTO(user.getId());
    }

}
