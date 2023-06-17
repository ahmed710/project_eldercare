/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;
import com.esprit.entities.User;

/**
 *
 * @author iheb
 */
public class Connecter {
    private static User userConnected;

    public static User getUserConnected() {
        return userConnected;
    }

    public static void setUserConnected(User userConnected) {
        Connecter.userConnected = userConnected;
    }

    
    
    
    
}
