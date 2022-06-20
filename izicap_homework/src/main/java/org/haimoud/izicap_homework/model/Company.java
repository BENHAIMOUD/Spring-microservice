package org.haimoud.izicap_homework.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author AYOUB BENHAIMOUD
 *
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties   //Annotation used to indicate that the properties that do not match the json response should be ignored
public class Company {

    @Id
    private Long id;
    private String nic;
    private String geo_adresse;
    private String date_creation;
    private String FullName;
    private String numero_tva_intra;
}
