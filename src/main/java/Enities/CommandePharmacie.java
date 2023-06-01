/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Enities;

/**
 *
 * @author houcem
 */
public class CommandePharmacie {
    
    private int ID_commande;
    private int ID_pharmacie;
    private int quantite;
    private boolean disponibilite;

    public CommandePharmacie(int ID_commande, int ID_pharmacie, int quantite, boolean disponibilite) {
        this.ID_commande = ID_commande;
        this.ID_pharmacie = ID_pharmacie;
        this.quantite = quantite;
        this.disponibilite = disponibilite;
    }

    public int getID_commande() {
        return ID_commande;
    }

    public void setID_commande(int ID_commande) {
        this.ID_commande = ID_commande;
    }

    public int getID_pharmacie() {
        return ID_pharmacie;
    }

    public void setID_pharmacie(int ID_pharmacie) {
        this.ID_pharmacie = ID_pharmacie;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public boolean isDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    @Override
    public String toString() {
        return "CommandePharmacie{" +
                "ID_commande=" + ID_commande +
                ", ID_pharmacie=" + ID_pharmacie +
                ", quantite=" + quantite +
                ", disponibilite=" + disponibilite +
                '}';
    }
}

