package com.brunolima.stoom.controller;

import com.brunolima.stoom.dto.AddressDTO;
import com.brunolima.stoom.dto.MessageResponseDTO;
import com.brunolima.stoom.entity.Address;
import com.brunolima.stoom.service.AddressService;
import com.brunolima.stoom.utils.AddressUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class AddressControllerTest {

    private static final String ADDRESS_API_URL_PATH = "/api/v1/addresses";
    private MockMvc mockMvc;

    @Mock
    private AddressService addressService;

    @InjectMocks
    private AddressController addressController;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(addressController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers(((viewName, locale) -> new MappingJackson2JsonView()))
                .build();
    }

    @Test
    void testWhenPOSTisCalledTheanAAddrresBeCreated() throws Exception {
        AddressDTO addressDTO = AddressUtils.createFakeAddressDTO();
        MessageResponseDTO expectedMessageResponse = MessageResponseDTO.builder()
                .message("Address created with ID: "+addressDTO.getId())
                .build();

        when(addressService.create(addressDTO)).thenReturn(expectedMessageResponse);
        mockMvc.perform(post(ADDRESS_API_URL_PATH)
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsBytes(addressDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", Is.is(expectedMessageResponse.getMessage())));
    }

    @Test
    void testWhenIsCalledUpdate() throws Exception {
        Long idAddress = Long.valueOf(1);
        AddressDTO addressDTO = AddressUtils.createFakeAddressDTO();

        mockMvc.perform(MockMvcRequestBuilders
                .put(ADDRESS_API_URL_PATH+"/{id}", idAddress)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsBytes(addressDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void testWhenIsCalledDelete() throws Exception {
        Long idAddress = Long.valueOf(1);
        mockMvc.perform(MockMvcRequestBuilders
                .delete(ADDRESS_API_URL_PATH+"/{id}", idAddress)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
