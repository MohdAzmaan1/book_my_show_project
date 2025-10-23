package com.example.BookMyShow.Controller;

import com.example.BookMyShow.EntryDTOs.MovieEntryDTO;
import com.example.BookMyShow.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
            String response = "Movie is not added";
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/trending")
    public ResponseEntity<List<String>> getTrendingMovies(@RequestParam(defaultValue = "10") int limit){
        try {
            Set<String> trendingMovies = movieService.getTrendingMovies(limit);
            return new ResponseEntity<>(new ArrayList<>(trendingMovies), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/trending-with-counts")
    public ResponseEntity<Map<String, Long>> getTrendingMoviesWithCounts(@RequestParam(defaultValue = "10") int limit) {
        try {
            Map<String, Long> trendingMovies = movieService.getTrendingMoviesWithCounts(limit);
            return new ResponseEntity<>(trendingMovies, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
