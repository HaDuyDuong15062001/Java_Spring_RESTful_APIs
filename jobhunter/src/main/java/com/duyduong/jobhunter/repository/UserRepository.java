package com.duyduong.jobhunter.repository;

import com.duyduong.jobhunter.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User findByEmail(String username);

    boolean existsByEmail(String email);

    @Override
    boolean existsById(Long id);

    User findByRefreshTokenAndEmail(String token, String email);
}
