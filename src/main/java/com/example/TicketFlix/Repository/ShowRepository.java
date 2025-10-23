package com.example.TicketFlix.Repository;

import com.example.TicketFlix.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show,Integer> {
}
