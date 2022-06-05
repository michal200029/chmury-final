package pl.opalka.skiecommenrcebacked.rest.model;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
public class CustomerApiDto {
    @Min(2)
    String firstName;
    @Min(2)
    String lastName;
    @Email
    String email;
    @Size(max = 9, min = 9)
    String phoneNumber;
}