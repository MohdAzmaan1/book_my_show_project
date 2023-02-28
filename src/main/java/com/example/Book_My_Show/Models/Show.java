package com.example.Book_My_Show.Models;

import com.example.Book_My_Show.Genres.ShowType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shows")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate showDate;

    private LocalTime showTime;

    @CreationTimestamp
    private Date createdOn;

    @UpdateTimestamp
    private Date updatedOn;

    @Enumerated(value = EnumType.STRING)
    private ShowType showType;

    //Child wrt Theater
    @ManyToOne
    @JoinColumn
    private Theater theater;

    //Child wrt to Movie
    @ManyToOne
    @JoinColumn
    private Movie movie;

    //Show is parent wrt tickets
    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    private List<Ticket>listOfBookedTickets  = new ArrayList<>();

    ////Show is parent wrt show seats
    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    private List<ShowSeat>listOfShowSeats  = new ArrayList<>();
}
