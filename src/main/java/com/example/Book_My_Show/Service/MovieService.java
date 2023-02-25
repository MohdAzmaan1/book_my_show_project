package com.example.Book_My_Show.Service;

import com.example.Book_My_Show.Convertors.MovieConvertor;
import com.example.Book_My_Show.EntryDTOs.MovieEntryDTO;
import com.example.Book_My_Show.Models.Movie;
import com.example.Book_My_Show.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieEntryDTO movieEntryDTO){
        Movie movie = MovieConvertor.convertDtoToEntity(movieEntryDTO);
        movieRepository.save(movie);

        return "Movie added Successfully";
    }

}
