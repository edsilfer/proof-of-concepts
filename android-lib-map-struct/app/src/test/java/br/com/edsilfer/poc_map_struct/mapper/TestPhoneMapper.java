package br.com.edsilfer.poc_map_struct.mapper;

import org.junit.Test;

import br.com.edsilfer.poc_map_struct.domain.Phone;
import br.com.edsilfer.poc_map_struct.dto.PhoneDTO;
import br.com.edsilfer.poc_map_struct.util.ResourceProvider;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by edgar on 05/07/17.
 */

public class TestPhoneMapper {

    @Test
    public void phone_dto_must_have_same_attributes_as_domain_phone () {
        Phone source = ResourceProvider.provideMockedPhone();
        PhoneDTO target = PhoneMapper.INSTANCE.transformFrom(source);

        assertThat(source.getCountry(), is(target.getCountry()));
        assertThat(source.getArea(), is(target.getArea()));
        assertThat(source.getNumber(), is(target.getNumber()));
    }
}
