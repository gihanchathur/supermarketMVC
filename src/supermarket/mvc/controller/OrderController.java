/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package supermarket.mvc.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import supermarket.mvc.db.DBConnection;
import supermarket.mvc.model.OrderDetailsModel;
import supermarket.mvc.model.OrderModel;

/**
 *
 * @author Gihan
 */
public class OrderController {

    public String placeOrder(OrderModel order, ArrayList<OrderDetailsModel> oredrDetailModels) throws SQLException {
            Connection connection = DBConnection.getInstance().getConnection();
            
            try{
                 boolean isOrderDetailsSaved;
                 boolean isOrderSaved=false;
            connection.setAutoCommit(false);
            String orderQuery="INSERT INTO orders VALUES(?,?,?)";
            String orderDetailsQuery="INSERT INTO orderdetail VALUES(?,?,?,?)";
             String itemReduceQuery="UPDATE item SET QtyOnHand=QtyOnHand-? WHERE ItemCode=?";
             
             PreparedStatement statementForOrder = connection.prepareStatement(orderQuery);
             statementForOrder.setString(1, order.getOrderId());
             statementForOrder.setString(2, order.getOrderDate());
             statementForOrder.setString(3, order.getCustId());
            
             if(statementForOrder.executeUpdate()>0){
                 isOrderSaved=true;
                 for(OrderDetailsModel orderDetailModel:oredrDetailModels){
             PreparedStatement statementForOrderDetails = connection.prepareStatement(orderDetailsQuery);
             statementForOrderDetails.setString(1, order.getOrderId());
             statementForOrderDetails.setString(2,orderDetailModel.getItemCode() );
             statementForOrderDetails.setInt(3,orderDetailModel.getOrderQty() );
             statementForOrderDetails.setInt(3,orderDetailModel.getDiscount() );
             if(!(statementForOrderDetails.executeUpdate()>0)){
                isOrderDetailsSaved=false;
                connection.rollback();
                 return "error while saving order details";
             }
             }
                 isOrderDetailsSaved=true;
                  for(OrderDetailsModel orderDetailModel:oredrDetailModels){
             PreparedStatement statementForReduceItems = connection.prepareStatement(itemReduceQuery);
             statementForReduceItems.setInt(1, orderDetailModel.getOrderQty());
             statementForReduceItems.setString(2, orderDetailModel.getItemCode());
             if(!(statementForReduceItems.executeUpdate()>0)){
                 connection.rollback();
                 return "error while updating items";
             } 
             connection.commit();
             return "Success";
             }
             }else{
             connection.rollback();
             return "error while saving order";
             }
            } catch (SQLException ex) {
                connection.rollback();
                ex.printStackTrace();
                return ex.getMessage();
        }finally{
            connection.setAutoCommit(true);
            }
        return null;
    }
    
}
