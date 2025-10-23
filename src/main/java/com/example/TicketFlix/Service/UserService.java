package com.example.TicketFlix.Service;

import com.example.TicketFlix.Convertors.UserConvertor;
import com.example.TicketFlix.EntryDTOs.UserEntryDTO;
import com.example.TicketFlix.Models.User;
import com.example.TicketFlix.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public String addUser(UserEntryDTO userEntryDTO) throws Exception, NullPointerException{
        User user = UserConvertor.convertDtoToEntity(userEntryDTO);

        userRepository.save(user);
        return "User added Successfully";
    }
}
