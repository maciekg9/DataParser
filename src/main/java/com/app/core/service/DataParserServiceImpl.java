package com.app.core.service;

import com.app.authentication.model.User;
import com.app.authentication.repository.UserRepository;
import com.app.core.model.DataParser;
import com.app.core.repository.DataParserRepository;
import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class DataParserServiceImpl implements DataParserService {

    @Autowired
    private DataParserRepository dataParserRepository;

    @Autowired
    private UserRepository userRepository;


    Map<Long, DataParser> database = new HashMap<>();

    @Override
    public void saveData(DataParser dataParser) {
        try {
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = this.userRepository.findByUsername(name);

            Parser parser = new Parser(TimeZone.getTimeZone("GMT"));
            List<DateGroup> dg = parser.parse(dataParser.getDate());
            Date time = dg.get(0).getDates().get(0);

            dataParser.setDate(time.toString());
            dataParser.setTitle(dataParser.getTitle());
            dataParser.setUser(user);
            dataParserRepository.save(dataParser);

        }

        catch (Exception e){
            e.printStackTrace();
        }
    }
    }



