package org.haimoud.izicap_homework.service;


import org.haimoud.izicap_homework.model.Company;
import org.json.simple.parser.ParseException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author AYOUB BENHAIMOUD
 *
 */
public interface ParsingService {
    /**
     * Service to parse the Json response and convert it to
     * the desired model class
     * And Download the CSV file
     */

    Company getCompanyInfos(String id) throws URISyntaxException, ParseException, IOException;
    public void downloadCsv(HttpServletResponse response) throws IOException;

}
