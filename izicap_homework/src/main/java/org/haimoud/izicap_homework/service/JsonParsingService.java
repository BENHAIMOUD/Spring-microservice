package org.haimoud.izicap_homework.service;


import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.haimoud.izicap_homework.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class JsonParsingService implements  ParsingService{

    private static final String JSON_COMPANY_URL = "https://entreprise.data.gouv.fr/api/sirene/v3/etablissements/31302979500017";


    @Autowired
    private RestTemplate restTemplate;


    Company company = new Company();

    @Override
    public Company getCompanyInfos() throws URISyntaxException, ParseException, IOException {

        URI uri = new URI(JSON_COMPANY_URL);
        ResponseEntity<String> responseCompany = restTemplate.exchange(uri, HttpMethod.GET, null,String.class);

        org.json.JSONObject  json0 = new org.json.JSONObject(responseCompany.getBody());

        //System.out.println("JSON RESULTS:"+ json0);
        org.json.JSONObject json1 = json0.getJSONObject("etablissement");
        org.json.JSONObject json2 = json1.getJSONObject("unite_legale");


        company.setId((long) json1.getInt("id"));
        company.setDate_creation(json1.getString("date_creation"));
        company.setGeo_adresse(json1.getString("geo_adresse"));
        company.setNic(json1.getString("nic"));
        company.setFullName("Not Found IN THE API");
        company.setNumero_tva_intra(json2.getString("numero_tva_intra"));

        return company;
    }




}
