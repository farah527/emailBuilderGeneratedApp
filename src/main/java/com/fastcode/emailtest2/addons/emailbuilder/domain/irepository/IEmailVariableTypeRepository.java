package com.fastcode.emailtest2.addons.emailbuilder.domain.irepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fastcode.emailtest2.addons.emailbuilder.domain.model.EmailVariableTypeEntity;

public interface IEmailVariableTypeRepository extends JpaRepository<EmailVariableTypeEntity, Long> {

}
