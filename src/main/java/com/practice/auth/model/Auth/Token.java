package com.practice.auth.model.Auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tokens")
public class Token {

    @Id
    @Column(
            name = "token",
            nullable = false,
            unique = true
    )
    private String token;

    @Column(
            name = "user_id",
            nullable = false,
            unique = true
    )
    private UUID userId;

    @Column(
            name = "created_at",
            nullable = false
    )
    private Timestamp createdAt;

    @Column(
            name = "valid_till",
            nullable = false
    )
    private Timestamp validTill;

    private String generateRandomToken(){
        int randomIndex;
        String newToken = "";
        String tokenChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890!@$_";
        for(int i = 0 ; i < 64 ; i++){
            randomIndex = (int)(Math.random()*1000) % tokenChars.length();
            newToken = newToken + tokenChars.charAt(randomIndex);
        }
        return newToken;
    }

    public Timestamp getTimestamp(Integer days){
        Timestamp timestamp = new Timestamp(new Date().getTime());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());
        cal.add(Calendar.DAY_OF_MONTH, days);
        Timestamp newTimestamp = new Timestamp(cal.getTime().getTime());
        return newTimestamp;
    }
    public Token(UUID userId){
        this.userId = userId;
        this.token = generateRandomToken();
        this.createdAt = getTimestamp(0);
        this.validTill = getTimestamp(30);
    }
}
