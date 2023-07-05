package com.example.ichatserver.to;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="client")
public class ClientTo {
    @Id
    private String email;
    private String username;
    private String password;
}
