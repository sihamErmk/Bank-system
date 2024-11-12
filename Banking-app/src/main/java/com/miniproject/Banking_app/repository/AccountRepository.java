package com.miniproject.Banking_app.repository;

import com.miniproject.Banking_app.entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Long> {


    Optional<Account> findByAccountNumber(@Param("accountNumber")String accountNumber);
}
