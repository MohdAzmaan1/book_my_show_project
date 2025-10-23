package com.example.BookMyShow.Convertors;

import com.example.BookMyShow.EntryDTOs.TheaterEntryDTO;
import com.example.BookMyShow.Models.Theater;

public class TheaterConvertor {

    public static Theater convertDtoToEntity(TheaterEntryDTO theaterEntryDTO){
        return Theater.builder().location(theaterEntryDTO.getLocation()).name(theaterEntryDTO.getName()).build();
    }
}
