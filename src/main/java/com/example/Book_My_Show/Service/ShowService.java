package com.example.Book_My_Show.Service;

import com.example.Book_My_Show.Convertors.ShowConvertor;
import com.example.Book_My_Show.EntryDTOs.ShowEntryDTO;
import com.example.Book_My_Show.Genres.SeatType;
import com.example.Book_My_Show.Models.*;
import com.example.Book_My_Show.Repository.MovieRepository;
import com.example.Book_My_Show.Repository.ShowRepository;
import com.example.Book_My_Show.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    @Autowired
    ShowRepository showRepository;

    public String addShow(ShowEntryDTO showEntryDTO){
        // Create a showEntity
        Show show = ShowConvertor.convertDtoToEntity(showEntryDTO);

        int movieId = showEntryDTO.getMovieId();
        int theaterId = showEntryDTO.getTheaterId();

        Movie movie = movieRepository.findById(movieId).get();
        Theater theater = theaterRepository.findById(theaterId).get();


        //Setting the attribute of foreign key
        show.setMovie(movie);
        show.setTheater(theater);
        show.setShowDate(showEntryDTO.getLocalDate());
        show.setShowTime(showEntryDTO.getLocalTime());


        //Pending attributes the listOfShowSeatsEntity
        List<ShowSeat> showSeatList = createShowSeatEntity(showEntryDTO,show);
        show.setListOfShowSeats(showSeatList);

        show = showRepository.save(show);

        //Now we  also need to update the parent entities
        List<Show> showList = movie.getShowList();
        showList.add(show);
        movie.setShowList(showList);
        movieRepository.save(movie);

        List<Show> showList1 = theater.getShowList();
        showList1.add(show);
        theater.setShowList(showList1);
        theaterRepository.save(theater);

        return "The show has been added successfully";
    }

    private List<ShowSeat> createShowSeatEntity(ShowEntryDTO showEntryDTO, Show show){
        //Now the goal is to create the ShowSeatEntity
        //We need to set its attribute

        Theater theater = show.getTheater();

        List<TheaterSeats> theaterSeats = theater.getTheaterSeatsList();

        List<ShowSeat> seatList = new ArrayList<>();

        for(TheaterSeats theaterSeats1 : theaterSeats){

            ShowSeat showSeat = new ShowSeat();

            showSeat.setSeatNumber(theaterSeats1.getSeatNumber());
            showSeat.setSeatType(theaterSeats1.getSeatType());

            if(theaterSeats1.getSeatType().equals(SeatType.CLASSIC))
                showSeat.setPrice(showEntryDTO.getClassSeatPrice());

            else
                showSeat.setPrice(showEntryDTO.getPremiumSeatPrice());

            showSeat.setBooked(false);
            showSeat.setShow(show); //parent : foreign key for the showSeat Entity

            seatList.add(showSeat); //Adding it to the list
        }
        return seatList;
    }
}
