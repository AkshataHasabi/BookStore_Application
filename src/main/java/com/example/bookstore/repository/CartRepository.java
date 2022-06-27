package com.example.bookstore.repository;

import com.example.bookstore.entity.CartData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Java persistance of API specific extension of repository
 * it provides jpa related methods such as creating & delr=eting.
 */
@Repository
public interface CartRepository extends JpaRepository<CartData,Integer> {
    @Query(value="select * from cart_data where book_id = :bookId",nativeQuery =true)
    Optional<CartData> findByBookId(Integer bookId);

    @Query(value="select * from cart_data where user_id =:userId",nativeQuery =true)
    List<CartData> findByUserId(Integer userId);
}
