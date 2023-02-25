package com.example.Book_My_Show.Service;

import com.example.Book_My_Show.Convertors.UserConvertor;
import com.example.Book_My_Show.EntryDTOs.UserEntryDTO;
import com.example.Book_My_Show.Models.User;
import com.example.Book_My_Show.Repository.UserRepository;
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
