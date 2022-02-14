package com.project.springproject.repository;

import com.project.springproject.domain.Board;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BoardRepository {

    @PersistenceContext
    EntityManager em;

    public void save(Board board){
        if(board.getId() == null){
            em.persist(board);
        } else {
            em.merge(board);
        }

    }

    public Board findById(Long id){
        return em.find(Board.class, id);
    }

    public List<Board> findAll(){
        return em.createQuery("select b from Board b", Board.class)
                .getResultList();
    }

    public List<Board> findByWriter(String writer){
        return em.createQuery("select b from Board b where b.writer = :writer", Board.class)
                .setParameter("writer", writer)
                .getResultList();
    }

    public void deleteById(Long id){
        em.remove(em.find(Board.class, id));
    }
}
