package com.philately.repository;

import com.philately.model.entity.Stamp;
import com.philately.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StampRepository extends JpaRepository<Stamp,Long> {
    List<Stamp> findByAddedById(Long id);

    List<Stamp> findStampsByAddedByNot(User addedBy);
}
