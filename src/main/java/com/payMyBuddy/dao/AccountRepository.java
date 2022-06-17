package com.payMyBuddy.dao;

import com.payMyBuddy.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface AccountRepository extends CrudRepository<Account, UUID> {

}
