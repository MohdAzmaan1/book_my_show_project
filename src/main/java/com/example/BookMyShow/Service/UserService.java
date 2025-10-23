package com.example.BookMyShow.Service;

import com.example.BookMyShow.Convertors.UserConvertor;
import com.example.BookMyShow.EntryDTOs.UserEntryDTO;
import com.example.BookMyShow.Models.User;
import com.example.BookMyShow.Repository.UserRepository;
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
