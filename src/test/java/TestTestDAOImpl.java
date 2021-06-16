import com.DAO.DAOImpl.TestDAOImpl;
import com.DAO.TestDAO;
import com.entity.Results;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class TestTestDAOImpl {

    TestDAO testDAO = new TestDAOImpl();

    @Test
    public void testGiveAllTests () {
        List<com.entity.Test> testList = testDAO.giveAllTests(1);
        List<com.entity.Test> testList1 = testDAO.giveAllTests(1);
        assertEquals(testList.size(), testList1.size());
    }

    @Test
    public void testGetAllQuestion () {
        ArrayList<String> arrayList = testDAO.getAllQuestions();
        ArrayList<String> arrayList1 = testDAO.getAllQuestions();
        assertEquals(arrayList.size(), arrayList1.size());
    }

    @Test
    public void testGetResults () {
        List<Results> results = testDAO.getResults(1);
        List<Results> results1 = testDAO.getResults(1);
        assertEquals(results, results1);
    }

}
