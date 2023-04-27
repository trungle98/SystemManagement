package com.edu.greenwich.managementsystem.Repository;

import com.edu.greenwich.managementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    @Query(value = "select * from users where user_id =?1", nativeQuery = true)
    Optional<User> findByUserId(long userId);
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query(value = "delete from users where user_id =?1", nativeQuery = true)
    int DeleteByUserId(long userId);
}