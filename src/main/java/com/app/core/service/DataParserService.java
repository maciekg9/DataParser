package com.app.core.service;

import com.app.core.model.DataParser;

public interface DataParserService {
    void saveData (DataParser dataParser);

    void deleteData(DataParser dataParser);
}
