package org.example.effectivetask.util;

import lombok.experimental.UtilityClass;
import org.modelmapper.ModelMapper;

@UtilityClass
public class CustomModelMapper {

    private static final ModelMapper mapper = new ModelMapper();

    public static <E, D> E toEntity(D dto, Class<E> entityClass) {
        return mapper.map(dto, entityClass);
    }

    public static <E, D> D toDto(E entity, Class<D> dtoClass) {
        return mapper.map(entity, dtoClass);
    }
}
