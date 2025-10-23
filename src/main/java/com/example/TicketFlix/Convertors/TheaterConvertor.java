package com.example.TicketFlix.Convertors;

import com.example.TicketFlix.EntryDTOs.TheaterEntryDTO;
import com.example.TicketFlix.Models.Theater;

public class TheaterConvertor {

    public static Theater convertDtoToEntity(TheaterEntryDTO theaterEntryDTO){
        return Theater.builder().location(theaterEntryDTO.getLocation()).name(theaterEntryDTO.getName()).build();
    }
}
