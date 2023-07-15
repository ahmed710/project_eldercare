/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.esprit.utils;

/**
 *
 * @author 21693
 */
public enum Organe {
          CERVEAU ,
          COEUR ,
          RATE ,
          estomac ,
          INTESTINS ,
          ORGANES_GENITAUX ,
          VESSIE ,
          PANCREAS ,
          REINS ,
          FOIE ,
          POUMONS  ,
          THYROIDE ; 
              public static Organe fromString(String value) {
        for (Organe classification : Organe.values()) {
            if (classification.name().equalsIgnoreCase(value)) {
                return classification;
            }
        }
        throw new IllegalArgumentException("Invalid Organe value: " + value);
    }

}
