package edu.learn.config.jpa

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@Configuration
@EntityScan(basePackages = ["edu.learn.jpa.entities"])
@EnableJpaRepositories(basePackages = ["edu.learn.jpa.repos"])
class JPAConfig {}