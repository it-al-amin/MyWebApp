package com.mycompany;
import java.util.Collection;
import java.util.Optional;
import com.mycompany.user.User;
import com.mycompany.user.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)

public class UserRepositoryTests {

    @Autowired
    private UserRepository repo;

    @Test
    public void testAddNew() {
        User user = new User();
        user.setEmail("mdalamin18053@gmail.com");
        user.setPassword("12345678");
        user.setFirstName("Al Amin");
        user.setLastName("Miah");
        User savedUser = repo.save(user);
        Assertions.assertNotNull(savedUser);
        Assertions.assertTrue(savedUser.getId() > 0);

    }
    @Test
    public void testListAll(){
        Iterable<User> users = repo.findAll();
        //Assertions.assertThat(users).hasSizeGreaterThan(0);
        Assertions.assertTrue(((Collection<?>) users).size() > 0);
        for(User user:users)
        {
            System.out.println(user);
        }
    }
    @Test
    public void testUpdate(){
        Integer userId=1;
       Optional<User> optionalUser = repo.findById(userId);
       User user=optionalUser.get();
       user.setPassword("qwery1234");
       repo.save(user);
       User updateUser=repo.findById(userId).get();
        Assertions.assertTrue(updateUser.getPassword().equals("qwery1234"));
    }
@Test
public void testGet()
{
    Integer userId=4;
    Optional<User> optionalUser = repo.findById(userId);
    User user=optionalUser.get();
    Assertions.assertTrue(optionalUser.isPresent());
    System.out.println(optionalUser.get());
}
    @Test
    public void testDelete()
    {
        Integer userId=4;
        repo.deleteById(userId);
        Optional<User> optionalUser = repo.findById(userId);
        //User user=optionalUser.get();
        Assertions.assertTrue(optionalUser.isEmpty());

    }

}
