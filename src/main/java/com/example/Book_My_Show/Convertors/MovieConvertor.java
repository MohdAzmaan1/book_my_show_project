package com.example.Book_My_Show.Convertors;

import com.example.Book_My_Show.EntryDTOs.MovieEntryDTO;
import com.example.Book_My_Show.Models.Movie;

public class MovieConvertor {

    public static Movie convertDtoToEntity(MovieEntryDTO movieEntryDTO){
        return Movie.builder().movieName(movieEntryDTO.getMovieName()).genre(movieEntryDTO.getGenre()).language(movieEntryDTO.getLanguage()).duration(movieEntryDTO.getDuration())
                .rating(movieEntryDTO.getRating()).duration(movieEntryDTO.getDuration()).build();

    }
}
