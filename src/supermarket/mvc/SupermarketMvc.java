/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package supermarket.mvc;

import supermarket.mvc.view.CustomerView;
import supermarket.mvc.view.ItemView;
import supermarket.mvc.view.OrderView;

/**
 *
 * @author Gihan
 */
public class SupermarketMvc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       new CustomerView().setVisible(true); 
       new ItemView().setVisible(true);
        new OrderView().setVisible(true);
    }
    
}
