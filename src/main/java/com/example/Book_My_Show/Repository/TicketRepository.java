package com.example.Book_My_Show.Repository;

import com.example.Book_My_Show.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}
