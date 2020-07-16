package dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.epam.storozhuk.dao.EventDAO;
import com.epam.storozhuk.dao.TicketDAO;
import com.epam.storozhuk.dao.UserDAO;
import com.epam.storozhuk.domain.Event;
import com.epam.storozhuk.domain.Ticket;
import com.epam.storozhuk.domain.User;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring.xml")
public class TicketDAOTest {
    @Autowired
    private TicketDAO ticketRepository;
    @Autowired
    private UserDAO userRepository;
    @Autowired
    private EventDAO eventRepository;

    @Test
    public void testOnSuccessfulTicketsBooking() throws ParseException {
        Set<Ticket> bookingTickets = new LinkedHashSet<>();
        List<User> users = userRepository.getUsersList();
        List<Event> events = eventRepository.getEventList();
        bookingTickets.add(new Ticket(users.get(0), events.get(0), 5, new SimpleDateFormat("dd-MM-yyyy").parse("11-07-2020")));
        bookingTickets.add(new Ticket(users.get(1), events.get(1), 16, new SimpleDateFormat("dd-MM-yyyy").parse("14-11-2020")));
        ticketRepository.bookTickets(bookingTickets);
        Set<Ticket> bookedTickets = ticketRepository.getTickets();
        assertTrue(bookedTickets.size() == 2);
        Iterator<Ticket> ticketIterator = bookedTickets.iterator();
        assertTrue(ticketIterator.next().getEvent().equals(events.get(0)));
        assertTrue(ticketIterator.next().getEvent().equals(events.get(1)));
    }
}
