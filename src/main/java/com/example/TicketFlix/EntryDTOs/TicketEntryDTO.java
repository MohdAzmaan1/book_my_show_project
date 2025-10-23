package com.example.TicketFlix.EntryDTOs;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class TicketEntryDTO {
    private int showId;

    private List<String> requestedSeats = new ArrayList<>();

    private int userId;
}
