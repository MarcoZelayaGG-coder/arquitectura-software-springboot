package sv.edu.udb.estructuralimpia.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Funcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime fechaHora;
    private double precio = 0; //$

    //Relaciones
    @ManyToOne
    private Sala sala;
    @ManyToOne
    private Pelicula pelicula;
    @OneToMany(mappedBy = "funcion")
    private List<Ticket> tickets;

}
