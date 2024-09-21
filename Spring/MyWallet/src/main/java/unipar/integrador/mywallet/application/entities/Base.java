package unipar.integrador.mywallet.application.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@MappedSuperclass
@Data
public class Base {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private UUID id;

}
