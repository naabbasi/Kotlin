package edu.learn.launch

import edu.learn.config.main.MainConfig
import edu.learn.jpa.entities.User
import edu.learn.jpa.repos.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.dao.DataIntegrityViolationException

class Launch : MainConfig() {
    @Autowired
    lateinit var userRepo: UserRepo

    @Bean
    fun addDefaultUsers() : CommandLineRunner {
        return CommandLineRunner {
            try {
                userRepo.save(User(username = "nabbasi", password = "x"))
            }catch ( ex : DataIntegrityViolationException) {
                ex.printStackTrace()
            }

            userRepo.findAll().forEach { println(it) }
            println(userRepo.findByUsername("nabbasi"))
        }
    }
}

fun main(args: Array<String>) {
    runApplication<Launch>(*args)
}
