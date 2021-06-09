package com.brunolima.stoom.mapper;

import com.brunolima.stoom.dto.AddressDTO;
import com.brunolima.stoom.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper( AddressMapper.class );

    Address toModel(AddressDTO addressDTO);

    AddressDTO toDTO(Address address);

    List<AddressDTO> toListDTO(List<Address> address);

}
