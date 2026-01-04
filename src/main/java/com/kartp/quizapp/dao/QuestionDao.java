package com.kartp.quizapp.dao;

import com.kartp.quizapp.model.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    List<Question> findByCategory(String category);

    @Query(value ="SELECT * FROM question q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numQue",nativeQuery = true )
    List<Question> findRandomQuestionsByCategory(String category,int numQue); //will write our own native query for this

}
