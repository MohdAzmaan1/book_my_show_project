package com.example.TicketFlix.Convertors;

import com.example.TicketFlix.EntryDTOs.UserEntryDTO;
import com.example.TicketFlix.Models.User;

public class UserConvertor {

    //Static is kept to avoid calling it via objects/instances
    public static User convertDtoToEntity(UserEntryDTO userEntryDTO){

        return User.builder().age(userEntryDTO.getAge()).address(userEntryDTO.getAddress())
                   .email(userEntryDTO.getEmail()).mobileNumber(userEntryDTO.getMobileNumber()).name(userEntryDTO.getName()).build();
    }
}
