package com.example.Book_My_Show.Controller;

import com.example.Book_My_Show.EntryDTOs.TheaterEntryDTO;
import com.example.Book_My_Show.Service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    @Autowired
    TheaterService theaterService;

    @PostMapping("/add")
    public ResponseEntity<String> addTheater(@RequestBody TheaterEntryDTO theaterEntryDTO){
        try{
            String response = theaterService.addTheater(theaterEntryDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        }catch(Exception e){
            String response = "Theater is not created";
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
