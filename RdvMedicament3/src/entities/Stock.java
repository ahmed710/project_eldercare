/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author DONIG
 */
public class Stock {
    private int idStock;
    private String idPharmacie;
    private String idMedicament;
    private int quantite;

    public Stock(int idStock, String idPharmacie, String idMedicament, int quantite) {
        this.idStock = idStock;
        this.idPharmacie = idPharmacie;
        this.idMedicament = idMedicament;
        this.quantite = quantite;
    }

    public Stock() {
    }

    public Stock(String idPharmacie, String idMedicament, int quantite) {
        this.idPharmacie = idPharmacie;
        this.idMedicament = idMedicament;
        this.quantite = quantite;
    }

    public Stock(int idStock, int quantite) {
        this.idStock = idStock;
        this.quantite = quantite;
    }

    public Stock(int idStock) {
        this.idStock = idStock;
    }

    public int getIdStock() {
        return idStock;
    }

    public void setIdStock(int idStock) {
        this.idStock = idStock;
    }

    public String getIdPharmacie() {
        return idPharmacie;
    }

    public void setIdPharmacie(String idPharmacie) {
        this.idPharmacie = idPharmacie;
    }

    public String getIdMedicament() {
        return idMedicament;
    }

    public void setIdMedicament(String idMedicament) {
        this.idMedicament = idMedicament;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return  "Pharmacie ' "+ idPharmacie+" '" +" contient " + quantite +"  unités " +" du medicament "
                +"("+ idMedicament+")" ;
    }

   
    
}
