package com.hiberus;

import com.hiberus.clients.ClientsPizzas;
import com.hiberus.dto.PizzaDTO;
import com.hiberus.dto.UserDTO;
import com.hiberus.exceptions.UserBadRequestException;
import com.hiberus.exceptions.UserNotFoundException;
import com.hiberus.mappers.UserMapper;
import com.hiberus.models.UpdatePizza;
import com.hiberus.models.User;
import com.hiberus.repositories.UserRepository;
import com.hiberus.services.Impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CrudTest {
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ClientsPizzas clientsPizzas;
    private User userTest;
    private final UUID id = UUID.fromString("829e17e6-2222-3333-a76d-7167727c887e");

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.userService = new UserServiceImpl(this.userRepository, this.clientsPizzas, "LegoLas");
        userTest = new User(id, "nameTest", new TreeSet<>());
    }

    //region MODEL_AND_DTO
    @Test
    void pizzaDTOTest() {
        PizzaDTO pizzaDTO = new PizzaDTO("Pizzeberus");
        Assertions.assertEquals("Pizzeberus", pizzaDTO.getName());
    }

    @Test
    void userDTONameTest() {
        UserDTO userDTO = new UserDTO(new HashSet<>(), "Userberus");
        Assertions.assertEquals("Userberus", userDTO.getName());
    }

    @Test
    void userDTOPizzasTest() {
        UserDTO userDTO = new UserDTO(new HashSet<>(), "Userberus");
        userDTO.getPizzas().add(new PizzaDTO("Pizzeberus"));
        Set<PizzaDTO> pizzaDTOSet = new TreeSet<>();
        pizzaDTOSet.add(new PizzaDTO("Pizzeberus"));
        Assertions.assertEquals(pizzaDTOSet, userDTO.getPizzas());
    }

    @Test
    void userDTOSetNameTest() {
        UserDTO userDTO = new UserDTO(new HashSet<>(), "Userberus");
        userDTO.getPizzas().add(new PizzaDTO("Pizzeberus"));
        userDTO.setName("PizzaMaster");

        Assertions.assertEquals("PizzaMaster", userDTO.getName());
    }

    @Test
    void userMapperNameTest() {
        User userTest = new User(UUID.fromString("e75e0b1b-0c70-4e19-b0b7-fb5b47b2a5f9"), "Name", new TreeSet<>());
        UserDTO userDTO = UserMapper.INSTANCE.mapToDTO(userTest);
        Assertions.assertEquals(userTest.getName(), userDTO.getName());
    }

    @Test
    void updatePizzaUserIdTest() {
        UpdatePizza updatePizza = new UpdatePizza(UUID.fromString("e75e0b1b-0c70-4e19-b0b7-fb5b47b2a5f9"), "a9bf5cd6-0c72-43e9-9eb2-8d14e27f2c32");
        Assertions.assertEquals(UUID.fromString("e75e0b1b-0c70-4e19-b0b7-fb5b47b2a5f9"), updatePizza.getUserId());
    }

    @Test
    void updatePizzaPizzaIdTest() {
        UpdatePizza updatePizza = new UpdatePizza(UUID.fromString("e75e0b1b-0c70-4e19-b0b7-fb5b47b2a5f9"), "a9bf5cd6-0c72-43e9-9eb2-8d14e27f2c32");
        Assertions.assertEquals("a9bf5cd6-0c72-43e9-9eb2-8d14e27f2c32", updatePizza.getPizzaIdOrName());
    }

    @Test
    void userNoArgsConstructorTest() {
        User user = new User();
        Assertions.assertNotNull(user);
    }

    @Test
    void userBuilderNameTest() {
        User user = User.builder()
                .name("PizzaMaster")
                .build();
        Assertions.assertEquals("PizzaMaster", user.getName());
    }

    @Test
    void userBuilderPizzaTest() {
        Set<UUID> pizzaDTOSet = new TreeSet<>();
        UUID id = UUID.fromString("a9bf5cd6-0c72-43e9-9eb2-8d14e27f2c32");
        pizzaDTOSet.add(id);

        User user = User.builder()
                .favouritesPizzas(pizzaDTOSet)
                .build();
        Assertions.assertEquals(pizzaDTOSet, user.getFavouritesPizzas());
    }

    @Test
    void userBuilderIdTest() {
        UUID id = UUID.fromString("a9bf5cd6-4444-43e9-9eb2-8d14e27f2c32");
        User user = User.builder()
                .id(id)
                .build();
        Assertions.assertEquals(id, user.getId());
    }

    //endregion
    //region Service

    @Test
    void getUserTest() throws UserNotFoundException {
        when(userRepository.findById(any())).thenReturn(Optional.ofNullable(userTest));
        User user = userService.getUser(id);
        Assertions.assertEquals(id, user.getId());
    }

    @Test
    void getUserExceptionTest() {
        when(userRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.getUser(id));
    }

    @Test
    void createUserTest() throws UserBadRequestException {
        when(userRepository.save(any())).thenReturn(userTest);
        UserDTO user = userService.createUser(userTest);
        Assertions.assertEquals("nameTest", user.getName());
    }

    @Test
    void createUserExceptionTest() throws UserBadRequestException {
        User userWithBadName = new User(id, "1nameTest1", new TreeSet<>());
        assertThrows(UserBadRequestException.class, () -> userService.createUser(userWithBadName));
    }
    @Test
    void createUserExceptionEmptyNameTest() throws UserBadRequestException {
        User userWithBadName = new User(id, "", new TreeSet<>());
        assertThrows(UserBadRequestException.class, () -> userService.createUser(userWithBadName));
    }
    //endregion
}
