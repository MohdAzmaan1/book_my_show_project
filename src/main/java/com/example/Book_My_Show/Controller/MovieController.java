package com.example.Book_My_Show.Controller;

import com.example.Book_My_Show.EntryDTOs.MovieEntryDTO;
import com.example.Book_My_Show.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<String> addMovie(@RequestBody MovieEntryDTO movieEntryDTO){
        try{
            String response = movieService.addMovie(movieEntryDTO);
            return new ResponseEntity<>(response, HttpStatus.FOUND);
        }catch (Exception e){
            String response = "Movie not added";
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }
}
