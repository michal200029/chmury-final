package pl.opalka.skiecommenrcebacked.rest.model;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class AddressApiDto {
    @Min(5)
    String city;
    CountryApiDto country;
    @Min(5)
    String street;
    @Min(6)
    String zipCode;
}