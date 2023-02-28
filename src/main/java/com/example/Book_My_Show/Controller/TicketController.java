package com.example.Book_My_Show.Controller;

import com.example.Book_My_Show.EntryDTOs.TicketEntryDTO;
import com.example.Book_My_Show.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @PostMapping("/book")
    public ResponseEntity<String> addTicket(@RequestBody TicketEntryDTO ticketEntryDTO){
        try{
            String response = ticketService.addTicket(ticketEntryDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        }catch (Exception e){

            String response = "Ticket is not booked";
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
