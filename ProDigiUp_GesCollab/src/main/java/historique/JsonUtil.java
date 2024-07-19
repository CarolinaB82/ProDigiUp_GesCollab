/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package historique;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import entities.Collaborateur;
import entities.Partenaire;
import entities.Prestation;
import entities.ResponsableActivite;

/**
 *
 * @author cberge
 */
public class JsonUtil {

     private static final ObjectMapper mapper = new ObjectMapper();
     
     static {
        // Configurer le mapper pour ordonner les propriétés
       // mapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
    }

    public static String collaborateurToJson(Collaborateur collaborateur) {
        try {
            return mapper.writeValueAsString(collaborateur);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
    
     public static String prestationToJson(Prestation prestation) {
        try {
            return mapper.writeValueAsString(prestation);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String responsableActiviteToJson (ResponsableActivite responsableActivite) {
        try{
            return mapper.writeValueAsString (responsableActivite);
        }catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
    
     public static String partenaireToJson (Partenaire partenaire) {
        try{
            return mapper.writeValueAsString (partenaire);
        }catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}

