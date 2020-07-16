import java.util.Collections;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.epam.storozhuk.domain.User;
import com.epam.storozhuk.service.UserService;
import com.epam.storozhuk.status.UserStatus;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testOnCorrectGetUserById() {
        assertTrue("John Doe".equals(userService.getById(1).getName()));
        assertTrue("Mark Twain".equals(userService.getById(2).getName()));
    }

    @Test
    public void testOnCorrectGetUserByEmail() {
        assertTrue("John Doe".equals(userService.getUserByEmail("john@email.com").getName()));
        assertTrue("Morris Bean".equals(userService.getUserByEmail("morris123@email.com").getName()));
    }

    @Test
    public void testOnCorrectUserSave() {
        User user = new User("New User", "user@email.com", "1998-06-12", UserStatus.USER, Collections.emptySet());
        userService.save(user);
        User savedUser = userService.getUserByEmail("user@email.com");
        assertTrue("New User".equals(savedUser.getName()));
        assertTrue(savedUser.getId() == 4);
    }
}
