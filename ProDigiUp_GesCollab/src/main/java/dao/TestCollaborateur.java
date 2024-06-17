/* +
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package dao;

import entities.Collaborateur;



/**
 *
 * @author cberge
 */
public class TestCollaborateur {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // TODO code application logic here
        
CollaborateurDao collDao = new CollaborateurDao();
Collaborateur collab = collDao.read(1);
        System.out.println("collab : " + collab.getId() + " " + collab.getMatricule() + " " + collab.getNom() + " " + collab.getPrenom() + " " +
         " " + collab.getTelephone_personnel() + " " + collab.getStatut() + " " + collab.getCategorie() + " " + collab.getGenre() + " "
        + collab.getRqth() + " " + collab.getMetier());
        
        
        

        
        
      
    }
    
    
}
