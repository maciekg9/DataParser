package com.app.core.repository;

import com.app.core.model.DataParser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("dataParserRepository")
public interface DataParserRepository extends JpaRepository<DataParser, Long> {
}