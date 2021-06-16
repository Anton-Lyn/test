import com.DAO.DAOImpl.QuestionDAOImpl;
import com.DAO.QuestionDAO;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestQuestionDAOImpl {

    QuestionDAO questionDAO = new QuestionDAOImpl();

    @Test
    public void testGetIdByQuestion () {
        Integer s = questionDAO.getIdTestByQuestion("Сумма 2+2");
        assertNotNull(s);
    }

    @Test
    public void testGetIdByQuestionMore () {
        Integer s = questionDAO.getIdTestByQuestion("");
        Integer s1 = 0;
        assertEquals(s, s1);
    }
}
