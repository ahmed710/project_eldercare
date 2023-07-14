/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import java.util.List;

/**
 *
 * @author DONIG
 */
public interface IService<T> {
    public void ajouter(T o);
  //  public void modifier(T o);
        public boolean modifier(T o);

    public void supprimer(T o);
  //  public void supprimer(int id);
    public List<T> afficher();
  //   public List<T> affichernom();
    
}
