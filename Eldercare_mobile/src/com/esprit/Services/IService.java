/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.esprit.Services;

import java.util.List;

/**
 *
 * @author iheb
 */
public interface IService<T> {
    
    public boolean ajouter(T t);
    public boolean modifier(T t);
    public boolean supprimer(T t);
    public List<T> afficher();
}
