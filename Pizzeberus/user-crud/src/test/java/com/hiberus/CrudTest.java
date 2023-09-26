package com.hiberus;

import com.hiberus.dto.PizzaDTO;
import com.hiberus.dto.UserDTO;
import com.hiberus.mappers.UserMapper;
import com.hiberus.models.UpdatePizza;
import com.hiberus.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;

class CrudTest {
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
}
