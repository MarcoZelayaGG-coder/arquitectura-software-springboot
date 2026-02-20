package sv.edu.udb.estructuralimpia.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int cantidadEntradas;
    private double pago;

    //Relaciones
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Funcion funcion;
}
