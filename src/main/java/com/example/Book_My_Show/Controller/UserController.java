package com.example.Book_My_Show.Controller;

import com.example.Book_My_Show.EntryDTOs.UserEntryDTO;
import com.example.Book_My_Show.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserEntryDTO userEntryDTO){

        try{
            String response = userService.addUser(userEntryDTO);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (Exception e){
            String result = "User not added";
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }
    }
}
