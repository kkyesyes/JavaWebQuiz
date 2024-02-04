package src.com.kk.furns.entity;

import java.math.BigDecimal;

/**
 * @author KK
 * @version 1.0
 */
public class Furn {
    private Integer id;
    private String name;
    private String maker;
    private BigDecimal price;
    private Integer sales;
    private Integer inventory;
    private String picture = "https://img95.699pic.com/photo/60005/6018.jpg_wh860.jpg";


    public Furn() {
    }

    public Furn(Integer id, String name, String maker, BigDecimal price, Integer sales, Integer inventory, String picture) {
        this.id = id;
        this.name = name;
        this.maker = maker;
        this.price = price;
        this.sales = sales;
        this.inventory = inventory;
        if (null != picture && "".equals(picture)) {
            this.picture = picture;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Furn{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maker='" + maker + '\'' +
                ", price=" + price +
                ", sales=" + sales +
                ", inventory=" + inventory +
                ", picture='" + picture + '\'' +
                '}';
    }
}
