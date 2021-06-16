import com.DAO.DAOImpl.UserDAOImpl;
import com.DAO.UserDAO;
import com.entity.User;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;



public class TestUserDAOImpl {

    UserDAO userDAO;

    @Test
    public void testFindUserByLogin () {
        userDAO = UserDAOImpl.getInstance();
        Optional<User> user = userDAO.findUserByLogin("test@mail.ru");
        Optional<User> user1 = userDAO.findUserByLogin("");
        assertNotNull(user);
        assertNotNull(user1);
    }

    @Test
    public void testFindUserExistence () {
        userDAO = UserDAOImpl.getInstance();
        Optional<User> user = userDAO.findUserByLogin("test@mail.ru");
        assertNotNull(user);
    }

    @Test
    public void testFindUserExistence1 () {
        userDAO = UserDAOImpl.getInstance();
        Optional<User> user = userDAO.findUserByLogin("");
        assertNotNull(user);
    }

}
