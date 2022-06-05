package pl.opalka.skiecommenrcebacked.rest.model;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class CountryApiDto {
    @Min(5)
    String name;
}
