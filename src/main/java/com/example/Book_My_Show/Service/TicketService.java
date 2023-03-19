package com.example.Book_My_Show.Service;

import com.example.Book_My_Show.Convertors.TicketConvertor;
import com.example.Book_My_Show.EntryDTOs.DeleteTicketEntryDTO;
import com.example.Book_My_Show.EntryDTOs.TicketEntryDTO;
import com.example.Book_My_Show.Models.Show;
import com.example.Book_My_Show.Models.ShowSeat;
import com.example.Book_My_Show.Models.Ticket;
import com.example.Book_My_Show.Models.User;
import com.example.Book_My_Show.Repository.ShowRepository;
import com.example.Book_My_Show.Repository.TicketRepository;
import com.example.Book_My_Show.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.*;

@Service
public class TicketService {

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JavaMailSender javaMailSender;

    public String addTicket(TicketEntryDTO ticketEntryDTO) throws Exception{
        //1. Create TicketEntity from entryDto : Convert DTO ---> Entity
        Ticket ticket = TicketConvertor.convertDtoToEntity(ticketEntryDTO);

        //Validation : Check if the requested seats are available or not ?
        boolean isValidRequest = checkValidityOfRequestedSeats(ticketEntryDTO);

        if(!isValidRequest){
            throw new Exception("Requested seats are not available");
        }

        //Calculate the total amount :
        Show show = showRepository.findById(ticketEntryDTO.getShowId()).get();
        List<ShowSeat> seatList = show.getListOfShowSeats();
        List<String> requestedSeats = ticketEntryDTO.getRequestedSeats();

        int totalAmount = 0;
        for(ShowSeat showSeat:seatList){

            if(requestedSeats.contains(showSeat.getSeatNumber())){
                totalAmount = totalAmount + showSeat.getPrice();
                showSeat.setBooked(true);
                showSeat.setBookedAt(new Date());
            }
        }

        ticket.setTotalAmount(totalAmount);


        //Setting the other attributes for the ticketEntity
        ticket.setMovieName(show.getMovie().getMovieName());
        ticket.setShowDate(show.getShowDate());
        ticket.setShowTime(show.getShowTime());
        ticket.setTheaterName(show.getTheater().getName());


        //We need to set that string that talked about requested Seats
        String allottedSeats = getAllowedSeatsFromShowSeats(requestedSeats);
        ticket.setBookedSeat(allottedSeats);


        //Setting the foreign key attributes
        User user = userRepository.findById(ticketEntryDTO.getUserId()).get();
        ticket.setUser(user);
        ticket.setShow(show);

        ticket = ticketRepository.save(ticket);

        //Save the parent
        List<Ticket> ticketList = show.getListOfBookedTickets();
        ticketList.add(ticket);
        show.setListOfBookedTickets(ticketList);
        showRepository.save(show);


        List<Ticket> ticketList1 = user.getBookedTickets();
        ticketList1.add(ticket);
        user.setBookedTickets(ticketList1);
        userRepository.save(user);

        String body = "Hi, "+user.getName()+"\n\nThis is to confirm your ticket booking for the movie:- "+ticket.getMovieName()
                +"\nTicket id - "+ticket.getTicketId()+"\nBooked Seats - " +allottedSeats+"\nAmount of rupees - "
                +totalAmount+"\n\n\n"+"Thank you for using our services, have a wonderful day!";

        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("azmaan000@gmail.com");
        mimeMessageHelper.setTo(user.getEmail());
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject("Confirmation for your ticket booking");

        javaMailSender.send(mimeMessage);

        return "Ticket is successfully booked";
    }

    private String getAllowedSeatsFromShowSeats(List<String> requestedSeats){

        String result = "";
        int count = 0;

        for(int i = 0; i < requestedSeats.size();i++){
            if(i == requestedSeats.size() - 1){
                result = result + requestedSeats.get(i);
            }
            else
                result = result + requestedSeats.get(i) + ",";
        }
        return result;
    }


    private boolean checkValidityOfRequestedSeats(TicketEntryDTO ticketEntryDTO){

        int showId = ticketEntryDTO.getShowId();

        List<String> requestedSeats = ticketEntryDTO.getRequestedSeats();

        Show show = showRepository.findById(showId).get();

        List<ShowSeat> listOfSeats = show.getListOfShowSeats();

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

    public String cancelTicket(DeleteTicketEntryDTO deleteTicketEntryDTO)throws Exception {
        Ticket ticket = ticketRepository.findById(deleteTicketEntryDTO.getTicketId()).get();
        List<ShowSeat> showSeatEntityList = ticket.getShow().getListOfShowSeats();
        List<String> ticketsToBeDeleted = deleteTicketEntryDTO.getDeleteTicketList();

        String [] currTickets = ticket.getBookedSeat().split(",");

        int count = 0;
        for(String ticketName : currTickets)
            if(ticketsToBeDeleted.contains(ticketName))
                count ++;

        if(count != ticketsToBeDeleted.size())
            throw new Exception("Invalid data found !");

        Set<String> deletedTicketSet = new HashSet<>();
        for(ShowSeat seat : showSeatEntityList) {
            if(ticketsToBeDeleted.contains(seat.getSeatNumber())) {
                seat.setBooked(false);
                deletedTicketSet.add(seat.getSeatNumber());
            }
        }

        StringBuilder newBookedTickets = new StringBuilder();
        for(String tick : currTickets) {
            if(!deletedTicketSet.contains(tick)) {
                if(newBookedTickets.length() > 0) newBookedTickets.append(',');
                newBookedTickets.append(tick);
            }
        }

        Iterator it = deletedTicketSet.iterator();
        StringBuilder ticket1 = new StringBuilder();
        while(it.hasNext()) {
            ticket1.append(it.next());
        }

        int toBeDeleted = deletedTicketSet.size() * 200;
        ticket.setTotalAmount(ticket.getTotalAmount() - toBeDeleted);

        User user = ticket.getUser();
        String body = "Hi,  "+user.getName()+"\n\nThis is to confirm your booking cancellation."+"\nTicket id - "+ticket.getTicketId()+"\nCancelled Seats - "+ticket1+"\nAmount of rupees - "+toBeDeleted+" will be refunded in to your account in 6-7 working days\n\n\n"+"Have a wonderful day !";

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("azmaan000@gmail.com");
        mimeMessageHelper.setTo(user.getEmail());
        mimeMessageHelper.setText(body);
        mimeMessageHelper.setSubject("Confirmation for your ticket cancellation");

        javaMailSender.send(mimeMessage);


        if(newBookedTickets.length() == 0)  {
            ticketRepository.delete(ticket);
            userRepository.save(user);
            return ("Tickets has been successfully cancelled !");
        }

        ticket.setBookedSeat(newBookedTickets.toString());
        userRepository.save(ticket.getUser());

        return ("Tickets has been successfully cancelled !");
    }
}
