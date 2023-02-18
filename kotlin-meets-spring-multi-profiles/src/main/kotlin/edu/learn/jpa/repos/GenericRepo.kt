package edu.learn.jpa.repos

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean
import org.springframework.data.repository.PagingAndSortingRepository

@NoRepositoryBean
interface GenericRepo<T, ID> : CrudRepository<T, ID>, PagingAndSortingRepository<T, ID>
