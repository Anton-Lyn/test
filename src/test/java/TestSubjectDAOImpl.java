import com.DAO.DAOImpl.SubjectDAOImpl;
import com.DAO.SubjectDAO;
import com.entity.Subject;
import org.junit.Test;

import java.sql.Time;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestSubjectDAOImpl {

    SubjectDAO subjectDAO = new SubjectDAOImpl();

    @Test
    public void testGetAllSubject() {
        List<Subject> list = subjectDAO.getAllSubjects(1);
        List<Subject> list1 = subjectDAO.getAllSubjects(1);
        assertEquals(list.size(), list1.size());
    }

    @Test
    public void testGetTimeTest () {
        Time time = subjectDAO.getTimeTest(1);
        Time time1 = Time.valueOf("00:15:00");
        assertEquals(time1, time);
    }
}
