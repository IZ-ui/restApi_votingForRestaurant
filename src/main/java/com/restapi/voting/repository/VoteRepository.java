package com.restapi.voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.restapi.voting.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Query("SELECT v FROM Vote v WHERE v.user.id=:user_id AND v.date=:vote_date")
    Vote findByUserIdAndDate(@Param("user_id") int user_id, @Param("vote_date") LocalDate vote_date);


    List<Vote> findAllByUserId(@Param("user_id") int user_id);
}
