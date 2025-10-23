package com.example.TicketFlix.EntryDTOs;

import lombok.Data;

@Data
public class TheaterEntryDTO {

    private String name;

    private String location;

    private int classicSeatsCount;

    private int premiumSeatsCount;
}
