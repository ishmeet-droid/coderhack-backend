package com.coderhack.coderhackbackend.entity;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.coderhack.constants.Badges;

import lombok.NonNull;

@Document(collection = "coding_users")
public record User(@Id Long id, @NonNull String userName, double score, Set<Badges> badges) {    
}


