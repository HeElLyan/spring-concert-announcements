package ru.itis.services;

import ru.itis.models.Ticket;

import java.util.List;

public interface TicketService {
    List<Ticket> getTickets();
    void addNew(Long userId);
    void delete(Long userId);
    void changeToInProgress(Ticket ticket);
    void changToClosed(Long userId);
    Ticket getOne(Long userId);

}
