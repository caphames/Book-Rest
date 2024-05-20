package com.book.rest.BookApp.repository;

import com.book.rest.BookApp.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<BookModel, Long> {
}
