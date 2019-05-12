package com.app.core.repository;

import com.app.authentication.model.User;
import com.app.core.model.DataParser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("dataParserRepository")
public interface DataParserRepository extends JpaRepository<DataParser, Long> {
    public List<DataParser> findByUser(User user);

}