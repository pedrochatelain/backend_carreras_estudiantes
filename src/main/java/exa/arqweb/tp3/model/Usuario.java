package exa.arqweb.tp3.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Usuario {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private int id;

    @Column
    private String nombre;

    @Column
    private String password;

    @Column
    private String roles;

    public Usuario() {}

    @Override
    public String toString() {
        return "\nID: " + id +
                "\nNombre: " + nombre;
    }

}