package com.dylan.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.util.Set;

@Data

@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name="game_store")
@ToString
//@JsonIgnoreProperties(value = {"usersOwned"})
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="game_id")
    private int game_id;

    @Column(name = "title")
    private String title;

    @Column(name = "rating")
    private int rating;

    @Column(name = "genre")
    private String genre;


    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "userGames")
    @JsonBackReference
    private Set<User> usersOwned;

    @Tolerate

    public Game(int game_id, String title,String genre, int rating ) {
        this.game_id = game_id;
        this.title = title;
        this.rating = rating;
        this.genre = genre;
    }
}
