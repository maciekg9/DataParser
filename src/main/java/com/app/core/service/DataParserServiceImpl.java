package com.app.core.service;

import com.app.core.model.DataParser;
import com.app.core.repository.DataParserRepository;
import com.mdimension.jchronic.Chronic;
import com.mdimension.jchronic.utils.Span;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.authentication.model.User;

@Service
public class DataParserServiceImpl extends User implements DataParserService {

    @Autowired
    private DataParserRepository dataParserRepository;

    @Override
    public void saveData(DataParser dataParser) {

        Span span = Chronic.parse(dataParser.getDate());
        dataParser.setDate(span.toString());
        dataParser.setAction(dataParser.getAction());
//        dataParser.setUser(dataParser.getUser());
        dataParserRepository.save(dataParser);
    }

    @Override
    public void deleteData (DataParser dataParser){
        dataParserRepository.delete();
    }
}