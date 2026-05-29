package edu.learn.config.redis

import org.springframework.stereotype.Component
import redis.embedded.RedisServer
import java.io.IOException
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy


@Component
class EmbededRedis {
    private var redisServer: RedisServer? = null

    @PostConstruct
    @Throws(IOException::class)
    fun startRedis() {
        redisServer = RedisServer()
        redisServer!!.start()
    }

    @PreDestroy
    fun stopRedis() {
        redisServer!!.stop()
    }
}