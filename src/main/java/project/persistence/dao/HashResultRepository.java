package project.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import project.persistence.model.HashResult;

public interface HashResultRepository extends JpaRepository<HashResult, Long> {

    @Override
    void delete(HashResult hashResult);
}
