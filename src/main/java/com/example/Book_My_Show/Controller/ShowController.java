package com.example.Book_My_Show.Controller;

import com.example.Book_My_Show.EntryDTOs.ShowEntryDTO;
import com.example.Book_My_Show.Service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shows")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/add")
    public ResponseEntity<String> addShow(@RequestBody ShowEntryDTO showEntryDTO){
        try{
            String response = showService.addShow(showEntryDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        }catch (Exception e){
            String result = "Show not added";
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }
}
