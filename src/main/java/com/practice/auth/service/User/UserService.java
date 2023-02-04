package com.practice.auth.service.User;

import com.practice.auth.dto.ExeptionResponseDTO;
import com.practice.auth.dto.UserResponseDTO;
import com.practice.auth.model.Auth.Token;
import com.practice.auth.model.User.User;
import com.practice.auth.repository.TokenDao;
import com.practice.auth.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private static UserDao userDao;
    private static TokenDao tokenDao;
    @Autowired
    public UserService(UserDao userDao, TokenDao tokenDao) {
        UserService.userDao = userDao;
        UserService.tokenDao = tokenDao;
    }
    public Object getUserDetails(String token) {
        try{
            Optional<Token> userToken =  tokenDao.findById(token);
            UUID userId = userToken.get().getUserId();
            Optional<User> userDetails = userDao.findById(userId);
            User user = userDetails.get();
            return new UserResponseDTO(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
        }catch (Exception e){
            return new ExeptionResponseDTO("UnAuthorized!",401, "Please Login to access user details!");
        }
    }
}
