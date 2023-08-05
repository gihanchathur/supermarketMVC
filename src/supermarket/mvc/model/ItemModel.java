/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package supermarket.mvc.model;

/**
 *
 * @author Gihan
 */
public class ItemModel {
    
    private String itemCode;
    private String description;
    private String packSize;
    private Double unitPrice;
    private Integer qoh;

    public ItemModel(String string, String string1, String string2, String string3, double aDouble, String string4, String string5, String string6, String string7) {
    }

    public ItemModel(String itemCode, String description, String packSize, Double unitPrice, Integer qoh) {
        this.itemCode = itemCode;
        this.description = description;
        this.packSize = packSize;
        this.unitPrice = unitPrice;
        this.qoh = qoh;
    }

    public String getItemCode() {
        return itemCode;
    }

    public String getDescription() {
        return description;
    }

    public String getPackSize() {
        return packSize;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public Integer getQoh() {
        return qoh;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPackSize(String packSize) {
        this.packSize = packSize;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setQoh(Integer qoh) {
        this.qoh = qoh;
    }

    @Override
    public String toString() {
        return "ItemModel{" + "itemCode=" + itemCode + ", description=" + description + ", packSize=" + packSize + ", unitPrize=" + unitPrice + ", qoh=" + qoh + '}';
    }
}
