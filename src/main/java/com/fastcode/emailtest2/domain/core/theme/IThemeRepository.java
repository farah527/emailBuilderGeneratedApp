package com.fastcode.emailtest2.domain.core.theme;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.time.*;
@Repository("themeRepository")
public interface IThemeRepository extends JpaRepository<Theme, Long>,QuerydslPredicateExecutor<Theme> {

}

