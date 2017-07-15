package br.com.edsilfer.poc_map_struct.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

import br.com.edsilfer.poc_map_struct.domain.Contacts;
import br.com.edsilfer.poc_map_struct.domain.Phone;
import br.com.edsilfer.poc_map_struct.dto.ContactsDTO;
import br.com.edsilfer.poc_map_struct.dto.PhoneDTO;

@Mapper
public interface ContactsMapper {
    ContactsMapper INSTANCE = Mappers.getMapper( ContactsMapper.class );

    ContactsDTO transformFrom (Contacts contacts);

    //List<PhoneDTO> map (List<Phone> phone);
}
