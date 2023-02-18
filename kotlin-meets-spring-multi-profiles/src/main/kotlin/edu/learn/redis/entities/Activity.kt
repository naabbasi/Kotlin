package edu.learn.redis.entities

import org.springframework.data.redis.core.RedisHash

@RedisHash("activity")
class Activity (
    var id : String = "",
    var name : String = ""
){
    override fun toString(): String {
        return "Activity(id='$id', name='$name')"
    }
}
