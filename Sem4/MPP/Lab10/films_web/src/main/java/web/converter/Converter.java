package web.converter;

import core.model.BaseEntity;
import web.dto.BaseDto;

/**
 * @author diananoveanu
 */

public interface Converter<Model extends BaseEntity<Long>, Dto extends BaseDto> {

    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);

}

