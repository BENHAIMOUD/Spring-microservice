package org.haimoud.izicap_homework.service;


import org.haimoud.izicap_homework.model.Company;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;

public interface ParsingService {
    /**
     * Service to parse the Json response and convert it to
     * the desired model class
     */

    Company getCompanyInfos() throws URISyntaxException, ParseException, IOException;
}
