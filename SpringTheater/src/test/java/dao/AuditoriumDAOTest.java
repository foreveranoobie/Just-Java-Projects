package dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.epam.storozhuk.dao.AuditoriumDAO;
import com.epam.storozhuk.domain.Auditorium;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
public class AuditoriumDAOTest {
    @Autowired
    private AuditoriumDAO auditoriumRepository;

    @Test
    public void testOnGetAuditoriumByNumberCorrect() {
        Auditorium receivedAuditorium = auditoriumRepository.getByNumber(2);
        assertTrue(receivedAuditorium.getSeatsCount() == 20 && receivedAuditorium.getVipSeatsCount() == 10);
    }
}
