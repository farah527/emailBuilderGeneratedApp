package com.fastcode.emailtest2.addons.emailapi.domain.emailattachments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmailAttachmentsRepository extends JpaRepository<EmailAttachments, Long> {

}

