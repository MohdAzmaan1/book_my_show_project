package com.example.Book_My_Show.EntryDTOs;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class TicketEntryDTO {
    private int showId;

    private List<String> requestedSeats = new ArrayList<>();

    private int userId;
}
