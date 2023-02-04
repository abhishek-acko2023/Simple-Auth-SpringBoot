package com.practice.auth.service.Auth;

import com.practice.auth.dto.SignUpResponseDTO;
import com.practice.auth.model.User.User;
import com.practice.auth.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {

    private static UserDao userDao;

    @Autowired
    public SignUpService(UserDao userDao) {
        SignUpService.userDao = userDao;
    }


    boolean validMail(String email) {
        if (email.contains("@") && email.contains("."))
            return true;
        return false;
    }

    boolean validPassword(String password) {
        if (password.length() >= 8)
            return true;
        return false;
    }

    boolean checkUser(User user) {
        if (user.getFirstName().length() <= 3)
            return false;
        else if (!validMail(user.getEmail()))
            return false;
        else if (!validPassword(user.getPassword()))
            return false;
        return true;
    }

    public SignUpResponseDTO addUser(User user) {
        try{
            if (checkUser(user)) {
                userDao.save(user);
                return new SignUpResponseDTO("Successfully registered. You can Login Now");
            }
        }catch (Exception e){
            return new SignUpResponseDTO("Already Registered with same email address!");
        }
        return new SignUpResponseDTO("Failed! Fill all fields correctly.");
    }

}
