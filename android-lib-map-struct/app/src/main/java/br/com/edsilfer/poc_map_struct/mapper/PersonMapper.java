package br.com.edsilfer.poc_map_struct.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.edsilfer.poc_map_struct.domain.Address;
import br.com.edsilfer.poc_map_struct.domain.Contacts;
import br.com.edsilfer.poc_map_struct.domain.Person;
import br.com.edsilfer.poc_map_struct.dto.AddressDTO;
import br.com.edsilfer.poc_map_struct.dto.ContactsDTO;
import br.com.edsilfer.poc_map_struct.dto.PersonDTO;

@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper( PersonMapper.class );

    @Mapping(source = "dateOfBirth", target = "age")
    PersonDTO transformFrom (Person person);

    AddressDTO map (Address address);

    ContactsDTO map (Contacts contacts);
}
