package sv.edu.udb.estructuralimpia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sv.edu.udb.estructuralimpia.model.Pelicula;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

}
