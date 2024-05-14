package com.coderhack.coderhackbackend.entity;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.coderhack.coderhackbackend.constants.Badges;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Document(collection = "coding_users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String userId;
    @NonNull
    private String userName;
    private int score;
    private Set<Badges> badges;

    public User(String userName) {
        this.userName = userName;
        this.score = 0;
        this.badges = new HashSet<>();
    }

    public void awardBadges() {
        if (score >= 60) {
            
            badges.add(Badges.CODE_NINJA);
            badges.add(Badges.CODE_CHAMP);
            badges.add(Badges.CODE_MASTER);
        
        } else if (score >= 30) {
            badges.add(Badges.CODE_NINJA);
            badges.add(Badges.CODE_CHAMP);      
            
        } else if (score >= 1) {
            badges.add(Badges.CODE_NINJA);
        }
    }
}


