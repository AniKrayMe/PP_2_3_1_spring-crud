package web.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import web.model.User;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
}
