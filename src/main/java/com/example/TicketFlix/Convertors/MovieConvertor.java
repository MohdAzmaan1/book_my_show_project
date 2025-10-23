package com.example.TicketFlix.Convertors;

import com.example.TicketFlix.EntryDTOs.MovieEntryDTO;
import com.example.TicketFlix.Models.Movie;

public class MovieConvertor {

    public static Movie convertDtoToEntity(MovieEntryDTO movieEntryDTO){
        return Movie.builder().movieName(movieEntryDTO.getMovieName()).genre(movieEntryDTO.getGenre()).language(movieEntryDTO.getLanguage()).duration(movieEntryDTO.getDuration())
                .rating(movieEntryDTO.getRating()).duration(movieEntryDTO.getDuration()).trending(false).build();

    }
}
