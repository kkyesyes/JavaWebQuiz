package src.com.kk.furns.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author KK
 * @version 1.0
 */
public class Order {
    public static final String NOT_SEND = "未发货";
    public static final String SENDED = "已发货";
    public static final String ACCEPT = "已确认收货";
    private String id;
    private Integer memberId;
    private BigDecimal price;
    private String status;
    private Date datetime;

    public Order() {
    }

    public Order(String id, Integer memberId, BigDecimal price, String status) {
        this.id = id;
        this.memberId = memberId;
        this.price = price;
        this.status = status;
        this.datetime = new Date();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", member_id=" + memberId +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", datetime=" + datetime +
                '}';
    }
}
