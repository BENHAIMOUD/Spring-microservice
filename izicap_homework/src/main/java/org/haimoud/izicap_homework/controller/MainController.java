package org.haimoud.izicap_homework.controller;

import org.haimoud.izicap_homework.model.Company;
import org.haimoud.izicap_homework.service.ParsingService;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;
/**
 * @author AYOUB BENHAIMOUD
 *
 */
@Controller
@RequestMapping("/company-infos")
public class MainController {


    private final ParsingService parsingService;

    public MainController(ParsingService parsingService) {

        this.parsingService = parsingService;

    }

    /**
     * this function allows us to extract  the company infos from the API
     * @return Company.class
     * @throws URISyntaxException
     * @throws ParseException
     * @throws IOException
     */
    @GetMapping(value= "/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable("id") String id) throws URISyntaxException, ParseException, IOException {
        return ResponseEntity.status(HttpStatus.OK).body(parsingService.getCompanyInfos(id));
    }

    /**
     * this function allows us to download the company infos
     * @param response
     * @throws IOException
     */
    @GetMapping("/download")
    public void getCompaniesInfosInCsv(HttpServletResponse response) throws IOException {
        parsingService.downloadCsv(response);
    }
    
}
