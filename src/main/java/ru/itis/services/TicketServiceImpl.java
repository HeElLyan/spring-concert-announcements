package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.State;
import ru.itis.models.Ticket;
import ru.itis.repositories.TicketRepository;
import ru.itis.repositories.UserRepository;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Ticket> getTickets() {
        return ticketRepository.findAllByStateNot(State.CLOSED);
    }

    @Override
    public void addNew(Long userId) {
        Ticket ticket = Ticket.builder()
                .state(State.NEW)
                .user(userRepository.findOne(userId))
                .build();
        ticketRepository.save(ticket);
    }

    @Override
    public void delete(Long userId) {
        ticketRepository.delete(ticketRepository.findByUserId(userId));
    }

    @Override
    public void changeToInProgress(Ticket ticket) {
        ticketRepository.delete(ticket);
        ticket.setState(State.IN_PROGRESS);
        ticketRepository.save(ticket);
    }

    @Override
    public void changToClosed(Long userId) {
        Ticket ticket = ticketRepository.findByUserId(userId);
        delete(userId);
        ticket.setState(State.CLOSED);
        ticketRepository.save(ticket);
    }

    @Override
    public Ticket getOne(Long userId) {
        return ticketRepository.findByUserId(userId);
    }


}
