package com.example.TicketFlix.EntryDTOs;

import com.example.TicketFlix.Genres.ShowType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowEntryDTO {

    private LocalDate localDate;

    private LocalTime localTime;

    private ShowType showType;

    private int movieId;

    private int theaterId;

    private int classSeatPrice;

    private int premiumSeatPrice;

}
