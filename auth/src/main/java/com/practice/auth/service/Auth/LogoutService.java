package com.practice.auth.service.Auth;

import com.practice.auth.dto.ExeptionResponseDTO;
import com.practice.auth.dto.SuccessResponseDTO;
import com.practice.auth.repository.TokenDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogoutService {

    public static TokenDao tokenDao;

    @Autowired
    public LogoutService(TokenDao tokenDao) {
        LogoutService.tokenDao = tokenDao;
    }

    public Object logout(String token) {
        try{
            if(tokenDao.existsById(token)){
                tokenDao.deleteById(token);
            }
            else {
                return new ExeptionResponseDTO("NOT_FOUND", 404, "You are not logged in!");
            }

        }catch (Exception e){
            return new ExeptionResponseDTO("NOT_FOUND", 404, "You are not logged in!");
        }
        return new SuccessResponseDTO("LOGGED_OUT", 200, "Logged Out Successfully!");
    }
}
