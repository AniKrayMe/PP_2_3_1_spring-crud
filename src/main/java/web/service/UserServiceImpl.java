package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.User;
import web.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl {

    @Autowired
    private UsersRepository repository;


    public List<User> getAllUsers()   {
        List<User> result = (List<User>) repository.findAll();

        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<User>();
        }
    }


    public User getUserById(long id) {
        Optional< User > optional = repository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new RuntimeException(" Employee not found for id :: " + id);
        }
    }


    public User createOrUpdateUser(User user) {

        if(user.getId() == null)
        {
            user = repository.save(user);

            return user;
        }
        else
        {
            Optional<User> employee = repository.findById(user.getId());

            if(employee.isPresent())
            {
                User newEntity = employee.get();
                newEntity.setEmail(user.getEmail());
                newEntity.setFirstName(user.getFirstName());
                newEntity.setLastName(user.getLastName());

                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                user = repository.save(user);

                return user;
            }
        }
    }

    public void deleteUserById(long id) throws RuntimeException {
        Optional<User> employee = repository.findById(id);

        if(employee.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("No employee record exist for given id");
        }
    }
}
