package com.example.Book_My_Show.Convertors;

import com.example.Book_My_Show.EntryDTOs.TicketEntryDTO;
import com.example.Book_My_Show.Models.Ticket;

public class TicketConvertor {
    public static Ticket convertDtoToEntity(TicketEntryDTO ticketEntryDTO){
        return new Ticket();
    }
}
