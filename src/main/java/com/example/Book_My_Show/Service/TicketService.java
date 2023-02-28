package com.example.Book_My_Show.Service;

import com.example.Book_My_Show.Convertors.TicketConvertor;
import com.example.Book_My_Show.EntryDTOs.TicketEntryDTO;
import com.example.Book_My_Show.Models.Show;
import com.example.Book_My_Show.Models.ShowSeat;
import com.example.Book_My_Show.Models.Ticket;
import com.example.Book_My_Show.Models.User;
import com.example.Book_My_Show.Repository.ShowRepository;
import com.example.Book_My_Show.Repository.TicketRepository;
import com.example.Book_My_Show.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    public String addTicket(TicketEntryDTO ticketEntryDTO) throws Exception{
        Ticket ticket = TicketConvertor.convertDtoToEntity(ticketEntryDTO);

        //Validation : Check if the requested seats are available or not ?
        boolean isValidRequest = checkValidityOfRequestedSeats(ticketEntryDTO);

        if(!isValidRequest){
            throw new Exception("Requested seats are not available");
        }

        //Calculate the total amount :
        Show showEntity = showRepository.findById(ticketEntryDTO.getShowId()).get();
        List<ShowSeat> seatEntityList = showEntity.getListOfShowSeats();
        List<String> requestedSeats = ticketEntryDTO.getRequestedSeats();

        int totalAmount = 0;
        for(ShowSeat showSeatEntity:seatEntityList){

            if(requestedSeats.contains(showSeatEntity.getSeatNumber())){
                totalAmount = totalAmount + showSeatEntity.getPrice();
                showSeatEntity.setBooked(true);
                showSeatEntity.setBookedAt(new Date());
            }
        }

        ticket.setTotalAmount(totalAmount);


        //Setting the other attributes for the ticketEntity
        ticket.setMovieName(showEntity.getMovie().getMovieName());
        ticket.setShowDate(showEntity.getShowDate());
        ticket.setShowTime(showEntity.getShowTime());
        ticket.setTheaterName(showEntity.getTheater().getName());


        //We need to set that string that talked about requested Seats
        String allotedSeats = getAllowedSeatsFromShowSeats(requestedSeats);
        ticket.setBookedSeat(allotedSeats);


        //Setting the foreign key attributes
        User userEntity = userRepository.findById(ticketEntryDTO.getUserId()).get();

        ticket.setUser(userEntity);
        ticket.setShow(showEntity);

        //Save the parent

        List<Ticket> ticketEntityList = showEntity.getListOfBookedTickets();
        ticketEntityList.add(ticket);
        showEntity.setListOfBookedTickets(ticketEntityList);

        showRepository.save(showEntity);


        List<Ticket> ticketEntityList1 = userEntity.getBookedTickets();
        ticketEntityList1.add(ticket);
        userEntity.setBookedTickets(ticketEntityList1);

        userRepository.save(userEntity);


        return "Ticket has successfully been added";

    }

    private String getAllowedSeatsFromShowSeats(List<String> requestedSeats){

        String result = "";

        for(String seat :requestedSeats){

            result = result + seat +", ";

        }
        return result;

    }


    private boolean checkValidityOfRequestedSeats(TicketEntryDTO ticketEntryDTO){

        int showId = ticketEntryDTO.getShowId();

        List<String> requestedSeats = ticketEntryDTO.getRequestedSeats();

        Show showEntity = showRepository.findById(showId).get();

        List<ShowSeat> listOfSeats = showEntity.getListOfShowSeats();

        //Iterating over the list Of Seats for that particular show
        for(ShowSeat showSeat : listOfSeats){

            String seatNo = showSeat.getSeatNumber();

            if(requestedSeats.contains(seatNo)){

                if(showSeat.isBooked()){
                    return false; //Since this seat cant be occupied : returning false
                }
            }
        }
        //All the seats requested were available
        return true;

    }
}
