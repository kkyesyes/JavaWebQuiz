package src.com.kk.furns.entity;

import java.math.BigDecimal;

/**
 * 购物车单行数据
 * @author KK
 * @version 1.0
 */
public class CartItem {
    private Integer id;
    private String name;
    private BigDecimal price;
    private Integer count;
    private BigDecimal totalPrice;
    private String picture;

    public CartItem(Integer id, String name, BigDecimal price, Integer count, BigDecimal totalPrice, String picture) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
        this.totalPrice = totalPrice;
        if (null != picture && !("".equals(picture))) {
            this.picture = picture;
        } else {
            this.picture = "https://img95.699pic.com/photo/60005/6018.jpg_wh860.jpg";
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", totalPrice=" + totalPrice +
                ", picture='" + picture + '\'' +
                '}';
    }
}
