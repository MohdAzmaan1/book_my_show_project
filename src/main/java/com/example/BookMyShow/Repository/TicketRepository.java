package com.example.BookMyShow.Repository;

import com.example.BookMyShow.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}
