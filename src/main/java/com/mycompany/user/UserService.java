package com.mycompany.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.PushBuilder;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired private UserRepository repo;
    public List<User>listAll(){
        return (List<User>)repo.findAll();
    }

    public void save(User user) {
        repo.save(user);
    }
    public User get(Integer id) throws UserNotFoundException {
       Optional<User> result=repo.findById(id);
       if(result.isPresent())
       {
           return result.get();
       }
       throw new UserNotFoundException("Could not find any user with ID." + id);
    }
    public void delete(Integer id) throws UserNotFoundException {
        Optional<User> result=repo.findById(id);
        if(result.isPresent())
        {
            repo.deleteById(id);
        }
        else{
        throw new UserNotFoundException("Could not find any user with ID." + id);
        }

    }
}
