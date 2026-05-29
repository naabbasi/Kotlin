package edu.learn.redis.repos

import edu.learn.redis.entities.Activity
import org.springframework.data.repository.CrudRepository

interface ActivityRepo : CrudRepository<Activity, String>
