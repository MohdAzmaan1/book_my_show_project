package com.example.BookMyShow.EntryDTOs;

import com.example.BookMyShow.Genres.Genre;
import com.example.BookMyShow.Genres.Language;
import lombok.Data;

@Data
public class MovieEntryDTO {

    private String movieName;

    private double rating;

    private double duration;

    private Genre genre;

    private Language language;
}
