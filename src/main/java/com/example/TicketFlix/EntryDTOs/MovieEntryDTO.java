package com.example.TicketFlix.EntryDTOs;

import com.example.TicketFlix.Genres.Genre;
import com.example.TicketFlix.Genres.Language;
import lombok.Data;

@Data
public class MovieEntryDTO {

    private String movieName;

    private double rating;

    private double duration;

    private Genre genre;

    private Language language;
}
