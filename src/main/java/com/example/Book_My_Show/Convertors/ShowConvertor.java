package com.example.Book_My_Show.Convertors;

import com.example.Book_My_Show.EntryDTOs.ShowEntryDTO;
import com.example.Book_My_Show.Models.Show;

public class ShowConvertor {
    public static Show convertDtoToEntity(ShowEntryDTO showEntryDTO) {
        return Show.builder()
                .showDate(showEntryDTO.getLocalDate())
                .showTime(showEntryDTO.getLocalTime())
                .showType(showEntryDTO.getShowType())
                .build();
    }
}
