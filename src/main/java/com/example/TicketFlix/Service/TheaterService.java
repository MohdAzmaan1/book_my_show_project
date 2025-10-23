package com.example.TicketFlix.Service;

import com.example.TicketFlix.Convertors.TheaterConvertor;
import com.example.TicketFlix.EntryDTOs.TheaterEntryDTO;
import com.example.TicketFlix.Genres.SeatType;
import com.example.TicketFlix.Models.Theater;
import com.example.TicketFlix.Models.TheaterSeats;
import com.example.TicketFlix.Repository.TheaterSeatRepository;
import com.example.TicketFlix.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    TheaterSeatRepository theaterSeatRepository;

    @Autowired
    TheaterRepository theaterRepository;

    public String addTheater(TheaterEntryDTO theaterEntryDTO) throws Exception{
        Theater theater = TheaterConvertor.convertDtoToEntity(theaterEntryDTO);
        List<TheaterSeats> theaterSeatsList = createTheaterSeats(theaterEntryDTO, theater);
        theater.setTheaterSeatsList(theaterSeatsList);
        theaterRepository.save(theater);
        return "Theater added Successfully";
    }

    private List<TheaterSeats> createTheaterSeats(TheaterEntryDTO theaterEntryDto,Theater theater){
        int numberOfClassicSeats = theaterEntryDto.getClassicSeatsCount();
        int numberOfPremiumSeats = theaterEntryDto.getPremiumSeatsCount();

        List<TheaterSeats> theaterSeatEntityList = new ArrayList<>();

        //Created the classic Seats
        for(int count = 1; count <= numberOfClassicSeats; count++){

            //We need to make a new TheaterSeatEntity
            TheaterSeats theaterSeat = TheaterSeats.builder()
                    .seatType(SeatType.CLASSIC).seatNumber(count+"C")
                    .theater(theater).build();

            theaterSeatEntityList.add(theaterSeat);
        }

        //Create the premium Seats
        for(int count = 1; count <= numberOfPremiumSeats; count++){

            TheaterSeats theaterSeat = TheaterSeats.builder().
                    seatType(SeatType.PREMIUM).seatNumber(count+"P").theater(theater).build();

            theaterSeatEntityList.add(theaterSeat);
        }
        return theaterSeatEntityList;
    }
}
