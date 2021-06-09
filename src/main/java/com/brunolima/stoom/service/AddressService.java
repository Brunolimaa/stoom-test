package com.brunolima.stoom.service;

import com.brunolima.stoom.dto.AddressDTO;
import com.brunolima.stoom.dto.MessageResponseDTO;
import com.brunolima.stoom.entity.Address;
import com.brunolima.stoom.mapper.AddressMapper;
import com.brunolima.stoom.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    private final AddressMapper addressMapper = AddressMapper.INSTANCE;

    public MessageResponseDTO create(AddressDTO addressDTO) {
        Address addressToSave = addressMapper.toModel( addressDTO );
        Address saveAddress = addressRepository.save(addressToSave);
        return MessageResponseDTO.builder()
                .message("Address created with ID: " + saveAddress.getId())
                .build();
    }

    public List<AddressDTO> findAll() {
        List<Address> listAddress = addressRepository.findAll();
        List<AddressDTO> listAddressDTO = addressMapper.toListDTO(listAddress);
        return listAddressDTO;
    }

    public AddressDTO findById(Long id) {
        Address addressToDTO = addressRepository.findAddressById(id);
        return addressMapper.toDTO(addressToDTO);
    }

    public void update(AddressDTO addressDTO) {
        Address addressToSave = addressMapper.toModel( addressDTO );
        Address saveAddress = addressRepository.save(addressToSave);
    }

    public void delete(Long id) { ;
        addressRepository.deleteById(id);
    }
}
