package com.fastcode.emailtest2.addons.emailapi.domain.emailhistory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailHistoryRepository extends JpaRepository<EmailHistory, Long> {

}

