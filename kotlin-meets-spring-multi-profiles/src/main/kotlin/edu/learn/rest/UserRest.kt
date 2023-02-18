package edu.learn.rest

import edu.learn.jpa.entities.User
import edu.learn.jpa.repos.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration
import java.util.stream.Stream

/**
 * http://noman:8999/app/api/users/allStream
 * /app - servlet context
 * /api - webflux service endpoint
 */
@RestController
@RequestMapping("/api/users")
class UserRest {
    @Autowired
    lateinit var userRepo: UserRepo

    @GetMapping(path = ["/hello"])
    fun helloByQueryParam(@RequestParam name : String? = "Noman") : String {
        return "Hello $name by spring rest"
    }

    @GetMapping(path = ["/hello/{name}"])
    fun helloByPathParam(@PathVariable name : String) : String {
        return "Hello $name by spring rest"
    }

    @GetMapping(path = ["/hello/user/{name}"])
    fun helloByUser(@PathVariable name : String) : User {
        return User(username = name)
    }

    @GetMapping(path = ["/all"])
    fun users() : MutableList<User>? {
        return this.userRepo.findAll() as MutableList
    }

    @GetMapping(path = ["/allMono"])
    fun usersByMono() : Mono<MutableList<User>?> {
        return Mono.just(this.userRepo.findAll() as MutableList)
    }

    @GetMapping(path = ["/allFlux"])
    fun usersByFlux() : Flux<MutableList<User>?> {
        return Flux.just(this.userRepo.findAll() as MutableList)
    }

    @GetMapping(path = ["/allStream"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun usersByFluxStream() : Flux<MutableList<User>?> {
        val stream = Flux.fromStream(Stream.generate({ this.userRepo.findAll() as MutableList }))
        val interval = Flux.interval(Duration.ofSeconds(1))
        return Flux.zip(stream, interval).map { it.t1 }
    }
}