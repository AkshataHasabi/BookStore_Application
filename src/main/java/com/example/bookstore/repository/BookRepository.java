package com.example.bookstore.repository;

import com.example.bookstore.entity.BookData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Java persistance of API specific extension of repository
 * it provides jpa related methods such as creating & delr=eting.
 */
@Repository
public interface BookRepository extends JpaRepository<BookData,Integer> {
    /**
     * @Query:- in order to define SQL to execute for spring data repo method,
     * its value attribute contains the sql to execute, we can also use the native sql to define our query.
     */
    @Query(value = "select * from book_data where book_name= :bookName",nativeQuery=true)
    List<BookData> findBookByName(String bookName);

    @Query(value = "select * from book_data where auther_name=auther_name",nativeQuery=true)
    List<BookData> findBookByAutherName(String autherName);
    @Query(value = "select * from book_data ORDER BY price ASC",nativeQuery=true)
    List<BookData> sortBookDataAsc();
    @Query(value = "select * from book_data ORDER BY price DESC ",nativeQuery=true)
    List<BookData> sortBookDataDesc();
}
