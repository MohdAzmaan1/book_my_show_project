package com.example.Book_My_Show.Controller;

import com.example.Book_My_Show.EntryDTOs.DeleteTicketEntryDTO;
import com.example.Book_My_Show.EntryDTOs.TicketEntryDTO;
import com.example.Book_My_Show.Service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/cancel-ticket")
    public ResponseEntity<String> cancelTicket(@RequestBody DeleteTicketEntryDTO deleteTicketEntryDto) {
        try{
            String response = ticketService.cancelTicket(deleteTicketEntryDto);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
