package edu.learn.config.main

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@ComponentScan(basePackages = ["edu.learn.config.jpa","edu.learn.rest","edu.learn.config.redis"])
@SpringBootApplication
class MainConfig