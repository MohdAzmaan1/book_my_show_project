package com.example.TicketFlix.Convertors;

import com.example.TicketFlix.EntryDTOs.ShowEntryDTO;
import com.example.TicketFlix.Models.Show;

public class ShowConvertor {
    public static Show convertDtoToEntity(ShowEntryDTO showEntryDTO) {
        return Show.builder()
                .showDate(showEntryDTO.getLocalDate())
                .showTime(showEntryDTO.getLocalTime())
                .showType(showEntryDTO.getShowType())
                .build();
    }
}
