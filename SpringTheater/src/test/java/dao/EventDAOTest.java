package dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.epam.storozhuk.dao.EventDAO;
import com.epam.storozhuk.domain.Event;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
public class EventDAOTest {
    @Autowired
    private EventDAO eventRepository;

    @Test
    public void testOnCorrectDateRange() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date from = simpleDateFormat.parse("15-02-2020");
        Date to = simpleDateFormat.parse("01-06-2020");
        Set<Event> receivedEvents = eventRepository.getForDateRange(from, to);
        assertTrue(receivedEvents.size() == 1);
        assertTrue(receivedEvents.iterator().next().getDates().contains(simpleDateFormat.parse("20-05-2020")));
    }

    @Test
    public void testOnCorrectCurrentEvents() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date to = simpleDateFormat.parse("01-10-2020");
        Set<Event> receivedEvents = eventRepository.getNextEvents(to);
        assertTrue(receivedEvents.size() == 1);
        assertTrue(receivedEvents.iterator().next().getDates().contains(simpleDateFormat.parse("01-08-2020")));
        assertTrue("Film".equals(receivedEvents.iterator().next().getName()));
    }

}
