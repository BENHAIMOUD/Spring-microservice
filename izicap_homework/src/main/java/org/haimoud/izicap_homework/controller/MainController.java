package org.haimoud.izicap_homework.controller;

import org.haimoud.izicap_homework.model.Company;
import org.haimoud.izicap_homework.service.ParsingService;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.io.IOException;
import java.net.URISyntaxException;

@Controller
@RequestMapping("/company-infos")
public class MainController {

    @Autowired
    private ParsingService parsingService;


    @GetMapping
    public ResponseEntity<Company> getCompany() throws URISyntaxException, ParseException, IOException {
        return ResponseEntity.status(HttpStatus.OK).body(parsingService.getCompanyInfos());
    }
}
