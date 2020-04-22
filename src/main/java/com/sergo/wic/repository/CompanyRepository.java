package com.sergo.wic.repository;

import com.sergo.wic.entities.Company;
import com.sergo.wic.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByLogin(String login);
    Company findByPhone(String phone);
  //  @Q(value = "SELECT EXISTS(SELECT 1 FROM users WHERE id = user.id)")
    @Query(value = "select exists(select 1 from users where id = ?)", nativeQuery = true)
    boolean isCompanyOwner(@Param("user_id")Long user_id);

}
