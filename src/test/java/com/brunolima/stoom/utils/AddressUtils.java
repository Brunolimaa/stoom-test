package com.brunolima.stoom.utils;

import com.brunolima.stoom.dto.AddressDTO;
import com.brunolima.stoom.entity.Address;

public class AddressUtils {

    public static AddressDTO createFakeAddressDTO(){
        return AddressDTO.builder()
                .id(Long.valueOf(1))
                .streetName("Rua Teste")
                .number(1)
                .complement("Teste")
                .neighbourhood("Teste")
                .city("Teste")
                .state("DF")
                .country("Brasil")
                .zipcode("00000000")
                .latitude("123456")
                .longitude("123456")
                .build();
    }

    public static Address createFakeAddress(){
        return Address.builder()
                .id(Long.valueOf(1))
                .streetName("Rua Teste")
                .number(1)
                .complement("Teste")
                .neighbourhood("Teste")
                .city("Teste")
                .state("DF")
                .country("Brasil")
                .zipcode("00000000")
                .latitude("123456")
                .longitude("123456")
                .build();
    }
}
