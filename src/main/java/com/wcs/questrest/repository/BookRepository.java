package com.wcs.questrest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wcs.questrest.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
    public List<Book> findByTitleContainingOrDescriptionContaining(String inTitle, String inDesc);
}
