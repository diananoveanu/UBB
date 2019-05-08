package web.converter;

import web.dto.BaseDto;
import core.model.BaseEntity;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseConverter<Model extends BaseEntity<Long>, Dto extends BaseDto>
        implements Converter<Model, Dto> {


    public List<Long> convertModelsToIds(List<Model> models) {
        return models.stream().map(model -> model.getId()).collect(Collectors.toList());
    }

    public List<Long> convertDTOsToIds(List<Dto> dtos) {
        return dtos.stream().map(dto -> dto.getId()).collect(Collectors.toList());
    }

    public List<Dto> convertModelsToDtos(List<Model> models) {
        return models.stream().map(this::convertModelToDto).collect(Collectors.toList());
    }
}
