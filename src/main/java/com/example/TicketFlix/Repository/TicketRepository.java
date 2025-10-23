package com.example.TicketFlix.Repository;

import com.example.TicketFlix.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}
