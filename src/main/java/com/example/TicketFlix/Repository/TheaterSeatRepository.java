package com.example.TicketFlix.Repository;

import com.example.TicketFlix.Models.TheaterSeats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheaterSeatRepository extends JpaRepository<TheaterSeats, Integer> {

}
