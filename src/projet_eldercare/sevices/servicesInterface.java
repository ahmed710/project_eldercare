/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package projet_eldercare.sevices;

import java.util.List;

/**
 *
 * @author 21693
 * @param <T>
 */
public interface servicesInterface<T> {
    public void ajouter(T p);
    public void modifier(T p);
    public void supprimer(T p);
    public List<T> afficher();
}
