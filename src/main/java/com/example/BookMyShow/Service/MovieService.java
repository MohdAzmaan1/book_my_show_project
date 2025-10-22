package com.example.BookMyShow.Service;

import com.example.BookMyShow.Convertors.MovieConvertor;
import com.example.BookMyShow.EntryDTOs.MovieEntryDTO;
import com.example.BookMyShow.Models.Movie;
import com.example.BookMyShow.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    RedisService redisService;

    public String addMovie(MovieEntryDTO movieEntryDTO){
        Movie movie = MovieConvertor.convertDtoToEntity(movieEntryDTO);
        movieRepository.save(movie);
        return "Movie added Successfully";
    }

    public Set<String> getTrendingMovies(int limit) {
        return redisService.getTrendingMovies(limit);
    }

    public Map<String, Long> getTrendingMoviesWithCounts(int limit) {
        Set<ZSetOperations.TypedTuple<String>> trendingWithScores = redisService.getTrendingMoviesWithCounts(limit);
        Map<String, Long> result = new LinkedHashMap<>();

        for (ZSetOperations.TypedTuple<String> tuple : trendingWithScores) {
            result.put(tuple.getValue(), Objects.requireNonNull(tuple.getScore()).longValue());
        }

        return result;
    }
}
