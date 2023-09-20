package com.hiberus.mapper;

import com.hiberus.dto.PizzaDTO;
import com.hiberus.modelos.Pizza;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", imports = Pizza.class)
public interface PizzaMapper {
    PizzaMapper INSTANCE = Mappers.getMapper(PizzaMapper.class);

    PizzaDTO mapToDTO(Pizza pizza);
}
