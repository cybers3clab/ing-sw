package exampleDTO.api;

import exampleDTO.domain.Role;
import exampleDTO.domain.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MapperUnitTest {


    @Autowired
    private UserMapper mapper;

    @Test
    void toDto_shouldMapFromDomainToDTO() {
        String name = "Test";
        String password = "test";
        Role admin = new Role("admin");
        List<String> expectedRoles = Collections.singletonList("admin");

        List<Role> roles = new ArrayList<>();
        roles.add(admin);

        User user = new User(name, password, roles);

        UserDTO dto = mapper.toDto(user);

        assertEquals(name, dto.getName());
        assertEquals(expectedRoles, dto.getRoles());
    }

    @Test
    void toUser_shouldMapFromDTOToDomain() {
        String name = "Test";
        String password = "test";
        String role = "admin";

        UserCreationDTO dto = new UserCreationDTO();
        dto.setName(name);
        dto.setPassword(password);
        dto.setRoles(Collections.singletonList("admin"));

        User expectedUser = new User(name, password, new ArrayList<>());

        User user = mapper.toUser(dto);

        assertEquals(name, user.getName());
        assertEquals(expectedUser.getPassword(), user.getPassword());
        assertEquals(Collections.emptyList(), user.getRoles());
    }

}