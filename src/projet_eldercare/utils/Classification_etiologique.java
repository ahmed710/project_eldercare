/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package projet_eldercare.utils;

public enum Classification_etiologique {
    Maladies_par_agents_physiques, 
    Maladies_toxiques,
    Maladies_parasitaires,
    Maladies_infectieuses,
    Maladies_traumatiques,
    Maladies_dyscrasiques,
    Maladies_psychiques;
    
    public static Classification_etiologique fromString(String value) {
        for (Classification_etiologique classification : Classification_etiologique.values()) {
            if (classification.name().equalsIgnoreCase(value)) {
                return classification;
            }
        }
        throw new IllegalArgumentException("Invalid classification_etiologique value: " + value);
    }
}
