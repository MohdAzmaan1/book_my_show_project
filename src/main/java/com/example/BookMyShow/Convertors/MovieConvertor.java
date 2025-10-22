package com.example.BookMyShow.Convertors;

import com.example.BookMyShow.EntryDTOs.MovieEntryDTO;
import com.example.BookMyShow.Models.Movie;

public class MovieConvertor {

    public static Movie convertDtoToEntity(MovieEntryDTO movieEntryDTO){
        return Movie.builder().movieName(movieEntryDTO.getMovieName()).genre(movieEntryDTO.getGenre()).language(movieEntryDTO.getLanguage()).duration(movieEntryDTO.getDuration())
                .rating(movieEntryDTO.getRating()).duration(movieEntryDTO.getDuration()).trending(false).build();

    }
}
