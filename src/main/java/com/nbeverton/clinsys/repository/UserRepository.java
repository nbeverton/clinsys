package com.nbeverton.clinsys.repository;

import com.nbeverton.clinsys.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
