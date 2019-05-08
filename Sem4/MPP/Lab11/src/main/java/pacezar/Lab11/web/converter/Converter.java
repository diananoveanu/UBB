package pacezar.Lab11.web.converter;


import pacezar.Lab11.core.model.BaseEntity;
import pacezar.Lab11.web.dto.BaseDto;

/**
 * @author diananoveanu
 */

public interface Converter<Model extends BaseEntity<Long>, Dto extends BaseDto> {

    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);

}

