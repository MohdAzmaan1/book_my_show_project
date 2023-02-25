package com.example.Book_My_Show.Convertors;

import com.example.Book_My_Show.EntryDTOs.TheaterEntryDTO;
import com.example.Book_My_Show.Models.Theater;

public class TheaterConvertor {

    public static Theater convertDtoToEntity(TheaterEntryDTO theaterEntryDTO){
        return Theater.builder().location(theaterEntryDTO.getLocation()).name(theaterEntryDTO.getName()).build();
    }
}
