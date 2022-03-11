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
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "users")
@ToString
//@JsonIgnoreProperties(value = {"userGames"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int user_id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;


//    @OneToMany(mappedBy = "user")
//    Set<Collection> user_collection;



    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "user_collection",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "game_id")}
    )
    @JsonBackReference
    private Set<Game> userGames;

    @Tolerate
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
