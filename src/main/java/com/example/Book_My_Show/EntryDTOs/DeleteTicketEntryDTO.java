package com.example.Book_My_Show.EntryDTOs;

import lombok.Data;

import java.util.List;

@Data
public class DeleteTicketEntryDTO {
    private int ticketId;
    private List<String> deleteTicketList;
}
