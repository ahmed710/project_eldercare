/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.esprit.utils;

/**
 *
 * @author 21693
 */
public enum Classification_fonctionelle {
    Dysfonctionnements_moleculaires,
    Dysfonctionnements_cellulaires,
    Dysfonctionnements_organiques,
    Dysfonctionnements_corporel,
    Dysfonctionnements_mental ; 
    
    public static Classification_fonctionelle fromString(String value) {
        for (Classification_fonctionelle classification : Classification_fonctionelle.values()) {
            if (classification.name().equalsIgnoreCase(value)) {
                return classification;
            }
        }
        throw new IllegalArgumentException("Invalid classification_fonctionelle value: " + value);
    }
}
