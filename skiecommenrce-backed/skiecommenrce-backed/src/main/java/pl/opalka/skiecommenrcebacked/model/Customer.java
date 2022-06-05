package pl.opalka.skiecommenrcebacked.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;
}