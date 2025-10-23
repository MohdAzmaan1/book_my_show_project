package com.example.BookMyShow.Convertors;

import com.example.BookMyShow.EntryDTOs.ShowEntryDTO;
import com.example.BookMyShow.Models.Show;

public class ShowConvertor {
    public static Show convertDtoToEntity(ShowEntryDTO showEntryDTO) {
        return Show.builder()
                .showDate(showEntryDTO.getLocalDate())
                .showTime(showEntryDTO.getLocalTime())
                .showType(showEntryDTO.getShowType())
                .build();
    }
}
