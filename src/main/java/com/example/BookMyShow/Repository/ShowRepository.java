package com.example.BookMyShow.Repository;

import com.example.BookMyShow.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show,Integer> {
}
