package edu.learn.jpa.repos

import edu.learn.jpa.entities.User
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
interface UserRepo : GenericRepo<User, Long> {
    fun findByUsername(@Param("username") username: String) : User
}