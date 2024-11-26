package com.andersen.bookstore;

import com.andersen.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, String> {
    List<Book> findAllByOrderByIdAsc();

    @Modifying
    @Query("update Book book set book.isAvailable = :isAvailable where book.id = :id")
    void updateAvailability(@Param("id") String id, @Param("isAvailable") String isAvailable);

}
