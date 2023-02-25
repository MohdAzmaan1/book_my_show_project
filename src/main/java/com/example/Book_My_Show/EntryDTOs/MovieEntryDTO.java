package com.example.Book_My_Show.EntryDTOs;

import com.example.Book_My_Show.Genres.Genre;
import com.example.Book_My_Show.Genres.Language;
import lombok.Data;

@Data
public class MovieEntryDTO {

    private String movieName;

    private double rating;

    private double duration;

    private Genre genre;

    private Language language;
}
