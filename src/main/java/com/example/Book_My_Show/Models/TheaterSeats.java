package com.example.Book_My_Show.Models;

import com.example.Book_My_Show.Genres.SeatType;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TheaterSeats")
public class TheaterSeats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String seatNumber;

    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;


    @ManyToOne
    @JoinColumn
    private Theater theater;
}
