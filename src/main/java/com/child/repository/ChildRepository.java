package com.child.repository;

import com.child.model.Account;
import com.child.model.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildRepository extends JpaRepository<Child,Long> {
    List<Child> findChildByAccount(Account account);
}
