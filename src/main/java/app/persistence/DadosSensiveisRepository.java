package app.persistence;

import app.entity.DadosSensiveisEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DadosSensiveisRepository extends CrudRepository<DadosSensiveisEntity, Long> {
}
