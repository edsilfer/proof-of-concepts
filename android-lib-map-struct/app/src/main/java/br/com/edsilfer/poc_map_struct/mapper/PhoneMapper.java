package br.com.edsilfer.poc_map_struct.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.edsilfer.poc_map_struct.domain.Phone;
import br.com.edsilfer.poc_map_struct.dto.PhoneDTO;

/**
 * Created by edgar on 05/07/17.
 */
@Mapper
public interface PhoneMapper {
    PhoneMapper INSTANCE = Mappers.getMapper( PhoneMapper.class );

    PhoneDTO transformFrom (Phone phone);
}
