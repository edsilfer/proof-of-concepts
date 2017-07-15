package br.com.edsilfer.poc_map_struct.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.edsilfer.poc_map_struct.domain.Address;
import br.com.edsilfer.poc_map_struct.dto.AddressDTO;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper( AddressMapper.class );

    AddressDTO transformFrom (Address address);
}
