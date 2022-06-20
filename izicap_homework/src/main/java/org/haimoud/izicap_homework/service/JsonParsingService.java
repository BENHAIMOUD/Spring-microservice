package org.haimoud.izicap_homework.service;


import org.haimoud.izicap_homework.repository.CompanyRepository;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.haimoud.izicap_homework.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author AYOUB BENHAIMOUD
 *
 */
@Service
public class JsonParsingService implements  ParsingService{

    private static final String JSON_COMPANY_URL = "https://entreprise.data.gouv.fr/api/sirene/v3/etablissements/";


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CompanyRepository repository;


    Company company = new Company();

    @Override
    public Company getCompanyInfos(String id) throws URISyntaxException, ParseException, IOException {

        URI uri = new URI(JSON_COMPANY_URL+id);
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
        this.repository.save(company);

        return company;
    }

    public void downloadCsv(HttpServletResponse response) throws IOException {
        try {

            response.setContentType("text/csv");
            response.setHeader("Content-Disposition", "attachment; filename=company-infos.csv");

            // write to csv file //

            ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

            String[]  headings = {"id","nic","geo_adresse","date_creation","FullName","numero_tva_intra"};
            String[] pojoclassPropertyName = {"id","nic","geo_adresse","date_creation","FullName","numero_tva_intra"};


            csvWriter.writeHeader(headings);
            Company companyInfo =this.company;

            if(null!=companyInfo)
                csvWriter.write(companyInfo, pojoclassPropertyName);

            csvWriter.close();

        }catch(Exception e) {
            e.printStackTrace();

        }
        System.out.println("csv report downloaded successfully...........");
    }





}
