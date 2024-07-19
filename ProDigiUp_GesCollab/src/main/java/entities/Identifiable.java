/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package entities;

/**
 * Interface représentant une entité identifiée par un identifiant unique de
 * type Integer. Les classes implémentant cette interface doivent fournir des
 * méthodes pour récupérer et définir cet identifiant.
 *
 * @author cberge
 */
public interface Identifiable {

    Integer getId();

    void setId(Integer id);
}
