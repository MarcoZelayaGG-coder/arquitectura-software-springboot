package sv.edu.udb.estructuralimpia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sv.edu.udb.estructuralimpia.model.Funcion;

import java.util.List;
@Repository

public interface FuncionRepository extends JpaRepository<Funcion, Long> {
    List<Funcion> findFuncionByPeliculaId(Long id);
}
