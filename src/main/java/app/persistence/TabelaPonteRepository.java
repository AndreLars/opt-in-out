package app.persistence;

import app.entity.TabelaPonte;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TabelaPonteRepository extends CrudRepository<TabelaPonte, Long> {

    Optional<TabelaPonte> findTabelaPonteByUsuarioEntityId(Long usuarioEntityId);
}
