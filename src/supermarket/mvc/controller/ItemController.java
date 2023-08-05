/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package supermarket.mvc.controller;

import supermarket.mvc.model.ItemModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import supermarket.mvc.db.DBConnection;
/**
 *
 * @author Gihan
 */
public class ItemController {

    public String deleteItem(String itemCode) throws SQLException {
         Connection connection=DBConnection.getInstance().getConnection();
         String query="DELETE FROM Item WHERE ItemCode=?";
         PreparedStatement statement=connection.prepareStatement(query);
         statement.setString(1, itemCode);
         
         if(statement.executeUpdate()>0){
             return "Success";
         }else{
             return "Fail to delete";
         }
    }
    public String saveItem(ItemModel itemModel ) throws SQLException{
        Connection connection=DBConnection.getInstance().getConnection();
        String query="INSERT INTO Item VALUES(?,?,?,?,?)";
        PreparedStatement statement=connection.prepareStatement(query);
        statement.setString(1,itemModel.getItemCode());
        statement.setString(2, itemModel.getDescription());
        statement.setString(3, itemModel.getPackSize());
        statement.setDouble(4, itemModel.getUnitPrice());
        statement.setInt(5, itemModel.getQoh());
        
        if(statement.executeUpdate()>0){
            return "Success";
        }else{
        return "Fail";
        }
    }
    
    public ArrayList<ItemModel> getAllItems() throws SQLException{
         Connection connection=DBConnection.getInstance().getConnection();
         String query="SELECT * FROM Item";
         PreparedStatement statement=connection.prepareStatement(query);
         
         ArrayList<ItemModel> itemModels=new ArrayList<>();
         ResultSet rst=statement.executeQuery();
         while(rst.next()){
             ItemModel itemModel=new ItemModel(rst.getString(1),
                     rst.getString(2),
                     rst.getString(3),
                     rst.getDouble(4),
                     rst.getInt(5)
             );
             itemModels.add(itemModel);
         }
         
        return itemModels;
    }
    
     public ItemModel searchItem(String itemCode) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM Item WHERE ItemCode = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, itemCode);
        
        ResultSet rst = statement.executeQuery();
        while (rst.next()) {            
            return new ItemModel(rst.getString(1),
                    rst.getString(2), 
                    rst.getString(3), 
                    rst.getDouble(4), 
                    rst.getInt(5)
            );
        } 
        return null;
    }

    public String updateItem(ItemModel item)throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String query="UPDATE Item SET Description=?,PackSize=?,UnitPrice=?,QtyOnHand=?";
        PreparedStatement statement=connection.prepareStatement(query);
        statement.setString(1,item.getDescription());
        statement.setString(2, item.getPackSize());
        statement.setDouble(3, item.getUnitPrice());
        statement.setInt(4, item.getQoh());
        
        if(statement.executeUpdate()>0){
            return "Success";
        }
        else{
        return "Fail to update";
        }
    }
    
}
