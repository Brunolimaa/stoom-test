package com.brunolima.stoom.service;

import com.brunolima.stoom.dto.AddressDTO;
import com.brunolima.stoom.entity.Address;
import com.brunolima.stoom.repository.AddressRepository;
import com.brunolima.stoom.utils.AddressUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    @Test
    void testWhenGivenExistingDataReturnThis() {
        List<Address> listAddresses = new ArrayList<>();
        listAddresses.add(AddressUtils.createFakeAddress());
        when(addressRepository.findAll()).thenReturn(listAddresses);
        List<Address> listAddressesReturn = addressRepository.findAll();
        Assertions.assertEquals(listAddressesReturn.size(), listAddresses.size());
    }

    @Test
    void testWhenIsUpdateGiven() {
        Address address = AddressUtils.createFakeAddress();
        address.setNumber(5);
        when(addressRepository.save(address)).thenReturn(address);
        Address addressReturn = addressRepository.save(address);
        Assertions.assertEquals(addressReturn.getNumber(), address.getNumber());
    }
}
