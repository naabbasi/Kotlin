package edu.learn.jpa.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
@Table( name = "USERS")
data class User (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var user_id : Long = -1,
    @Column(unique = true)
    var username : String = "",
    @JsonIgnore
    var password : String = ""
) {
    override fun toString(): String {
        return "User(user_id=$user_id, username='$username')"
    }
}