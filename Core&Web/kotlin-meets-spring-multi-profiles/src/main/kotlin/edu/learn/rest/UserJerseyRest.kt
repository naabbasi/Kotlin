package edu.learn.rest

import edu.learn.jpa.entities.User
import edu.learn.jpa.repos.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import java.net.URI
import javax.websocket.server.PathParam

/**
 * this service can be acess via http://noman:8999/app/jersey/users/nabbasi
 * /app - servlet context
 * /jersey - endpoint of jersey rest service
 */
@Component
@RequestMapping("/users")
class UserJerseyRest {

    @Autowired
    lateinit var userRepo: UserRepo

    @GetMapping
    @RequestMapping("/user/{username}")
    fun getBook(@PathParam("username") username: String) : User {
        return User(1,"n",Thread.currentThread().name)
    }

    @GetMapping
    @RequestMapping("/userasync/{username}")
    @ManagedAsync
    fun getBookAsync(@Suspended asyncResponse : AsyncResponse, @PathParam("username") username: String) {
        val page = PageRequest.of(0,2)
        asyncResponse.resume(this.userRepo.findByPasswordContaining("xxx", page).content)
    }

    @PostMapping
    fun addBook(user: User): Response {
        userRepo.save(user)
        return Response.created(URI.create("/" + user.userId)).build()
    }

    @DeleteMapping
    @RequestMapping("/{id}")
    fun deleteBook(@PathParam("id") id: String): ResponseEntity {
        userRepo.delete(User(id.toLong()))
        return ResponseEntity.ok().build()
    }
}