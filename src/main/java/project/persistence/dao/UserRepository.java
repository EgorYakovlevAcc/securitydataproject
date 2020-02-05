package project.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import project.persistence.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByPasswordType(String passwordType);

    @Override
    void delete(User user);
}
