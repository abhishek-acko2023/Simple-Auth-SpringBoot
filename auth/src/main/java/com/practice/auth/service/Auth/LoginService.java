package com.practice.auth.service.Auth;

import com.practice.auth.dto.LoginResponseDTO;
import com.practice.auth.model.Auth.Login;
import com.practice.auth.model.Auth.Token;
import com.practice.auth.model.User.User;
import com.practice.auth.repository.TokenDao;
import com.practice.auth.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
public class LoginService {

    public static LoginService loginService;
    private static UserDao userDao;
    private static TokenDao tokenDao;

    @Autowired
    public LoginService(UserDao userDao, TokenDao tokenDao) {
        LoginService.userDao = userDao;
        LoginService.tokenDao = tokenDao;
    }

    public Timestamp getTimestamp(Integer days){
        Timestamp timestamp = new Timestamp(new Date().getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());
        cal.add(Calendar.DAY_OF_MONTH, days);
        return new Timestamp(cal.getTime().getTime());
    }

    public boolean userExists(String email){
        for(User user : userDao.findAll()){
            if(user.getEmail().equals(email))
                return true;
        }
        return false;
    }

    public UUID getUserId(String email){
        for(User user : userDao.findAll()){
            if(user.getEmail().equals(email))
                return user.getId();
        }
        return null;
    }
    public User getUser(UUID userId){
        for(User user : userDao.findAll()){
            if(user.getId().equals(userId))
                return user;
        }
        return null;
    }
    public boolean checkCredentials(Login login,UUID userId){
        User user = getUser(userId);
        if (login.getEmail().equals(user.getEmail())) {
            if (login.getPassword().equals(user.getPassword())) return true;
        }
        return false;
    }

    public boolean alreadyLoggedIn(UUID userId){
        for(Token token : tokenDao.findAll()){
            if(token.getUserId().equals(userId)){ // user logged in earlier without logout
                Timestamp currentTime = getTimestamp(0);
                int tokenValid = currentTime.compareTo(token.getValidTill());
                if (tokenValid >= 0) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public String getLoginToken (UUID userId) {
        for(Token token : tokenDao.findAll()){
            if(token.getUserId().equals(userId)){
                return token.getToken();
            }
        }
        return "";
    }

    public void saveToken(UUID userId, Token loginToken){
        for(Token token : tokenDao.findAll()){
            if(token.getUserId().equals(userId)){
                token.setToken(loginToken.getToken());
                return;
            }
        }
        tokenDao.save(loginToken);
    }
    public LoginResponseDTO login(Login login){
        LoginResponseDTO res = new LoginResponseDTO(false,"Email Not Found!");
        // check if user exists or not
        if(userExists(login.getEmail())){
            // get userId with email
            UUID userId = getUserId(login.getEmail());
            // check if login credentials are correct
            if(checkCredentials(login,userId)){
                // check if user already has a login token
                if(alreadyLoggedIn(userId)) // return login token if not expired
                    res = new LoginResponseDTO(true, "Logged in Successfully!", getLoginToken(userId));
                else{
                    // generate new token and return
                    Token loginToken = new Token(getUserId(login.getEmail()));
                    res = new LoginResponseDTO(true,"Logged in Successfully!",loginToken.getToken());
                    saveToken(userId,loginToken);
                }
            }
            else res = new LoginResponseDTO(false, "Invalid Credentials");
        }
        return res;
    }
}
