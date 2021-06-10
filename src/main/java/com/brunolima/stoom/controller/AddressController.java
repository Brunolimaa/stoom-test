package com.brunolima.stoom.controller;

import com.brunolima.stoom.dto.AddressDTO;
import com.brunolima.stoom.dto.GeocodeResultDTO;
import com.brunolima.stoom.dto.GeometryDTO;
import com.brunolima.stoom.dto.MessageResponseDTO;
import com.brunolima.stoom.entity.Address;
import com.brunolima.stoom.repository.AddressRepository;
import com.brunolima.stoom.service.AddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
@Api(value = "API REST - Stoom Address")
@CrossOrigin(origins = "*")
public class AddressController {

    private static final String API_KEY = "AIzaSyCj0cY2yEvVfYhAaTz3-P2MW-YRKmhz5Uw" ;
    @Autowired
    private AddressService addressService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    @ApiOperation(value="Create a new address")
    public MessageResponseDTO create(@RequestBody @Valid AddressDTO addressDTO) {
        callApiGoogleMap(addressDTO);
        return addressService.create(addressDTO);
    }

    @GetMapping("/")
    @ApiOperation(value="Return all address")
    public ResponseEntity<List<AddressDTO>> readAll() {
        List<AddressDTO> list = addressService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    @ApiOperation(value="Return one address")
    public ResponseEntity<AddressDTO> readId(@PathVariable Long id) {
        AddressDTO addressDTO = addressService.findById(id);
        return ResponseEntity.ok().body(addressDTO);
    }

    @PutMapping("/{id}")
    @ApiOperation(value="Update address selected")
    public ResponseEntity<MessageResponseDTO> update(@RequestBody AddressDTO addressDTO, @PathVariable Long id) {
        addressDTO.setId(id);
        callApiGoogleMap(addressDTO);
        addressService.update(addressDTO);
        return ResponseEntity.ok().body(MessageResponseDTO.builder().message("successfully update").build());
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value="Delete address selected")
    public ResponseEntity<MessageResponseDTO> deleteAddress(@PathVariable Long id){
        addressService.delete(id);
        return ResponseEntity.ok().body(MessageResponseDTO.builder().message("successfully deleted").build());
    }

    private void callApiGoogleMap(AddressDTO addressDTO) {
        if(addressDTO.getLatitude() == null && addressDTO.getLongitude() == null){
            GeocodeResultDTO geoCode = restTemplate.getForObject("https://maps.googleapis.com/maps/api/geocode/json?address="+addressDTO+"&key="+API_KEY, GeocodeResultDTO.class);
            addressDTO.setLatitude(geoCode.getResults().get(0).getGeometry().getLocation().getLat());
            addressDTO.setLongitude(geoCode.getResults().get(0).getGeometry().getLocation().getLng());
        }
    }



}
