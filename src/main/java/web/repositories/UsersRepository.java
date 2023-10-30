package web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import web.model.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
}
