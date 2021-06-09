package com.brunolima.stoom.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private Long id;

    @NotBlank
    private String streetName;

    @NotNull(message = "Number is mandatory")
    private Integer number;

    private String complement;

    @NotBlank(message = "Neighbourhood is mandatory")
    private String neighbourhood;

    @NotBlank(message = "City is mandatory")
    private String city;

    @NotBlank(message = "State is mandatory")
    private String state;

    @NotBlank(message = "Country is mandatory")
    private String country;

    @NotBlank(message = "Zipcode is mandatory")
    private String zipcode;

    private String latitude;

    private String longitude;
}
