package com.swapp.swapp.repository;

import com.swapp.swapp.model.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Books, Integer>{
    
    Optional<Books> findByTitulo(String titulo);

    Optional<Books> findByCategoria(String categoria);

    Page<Books> findAllByUser(Users user, Pageable pageable);

    @Query(value = "SELECT DISTINCT p.titulo FROM BOOKS_TABLE p WHERE p.titulo LIKE %:term% ORDER BY p.titulo ASC", nativeQuery = true)
    List<String> findAllTitlesByTerm(@Param("term") String term);

    @Query(value = "SELECT DISTINCT p.isbn FROM BOOKS_TABLE p WHERE p.isbn LIKE %:term% ORDER BY p.isbn ASC", nativeQuery = true)
    List<String> findAllIsbnByTerm(@Param("term") String term);

    Optional<Books> findById(Integer id);

    //@Query(value = "SELECT p.book FROM BOOKS_TABLE WHERE p.book IN (SELECT  FROM USERS_TABLE u WHERE (POW((u.longi - :coordx), 2) + POW((u.lat - :coordy), 2)) <= :max)", nativeQuery = true)
    //List<List<Books>> findCloseBooks(@Param("coordx") double coordx, @Param("coordy") double coordy, @Param("max") double max);
    

}
