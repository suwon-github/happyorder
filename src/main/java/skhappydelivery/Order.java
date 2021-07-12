package skhappydelivery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

@Entity
@Table(name="Order_table")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long orderId;
    private Long customerId;
    private String customerName;
    private String customerAddress;
    private Integer phoneNumber;
    private Long menuId;
    private Integer menuCount;
    private Integer menuPrice;
    private Long storeId;
    private String orderStatus;

    @PostPersist
    public void onPostPersist(){
        OrderPlaced orderPlaced = new OrderPlaced();
        BeanUtils.copyProperties(this, orderPlaced);
        orderPlaced.publishAfterCommit();

        //Following code causes dependency to external APIs
        // it is NOT A GOOD PRACTICE. instead, Event-Policy mapping is recommended.

        skhappydelivery.external.Payed Payed = new skhappydelivery.external.Payed();
        // mappings goes here

        Payed.setId(this.orderId);
        Payed.setTotalPrice(this.menuCount * this.menuPrice);
        

        OrderApplication.applicationContext.getBean(skhappydelivery.external.PayService.class)
            .payed(Payed);


    }

    @PostUpdate
    public void onPostUpdate(){
        OrderCanceled orderCanceled = new OrderCanceled();
        BeanUtils.copyProperties(this, orderCanceled);
        orderCanceled.publishAfterCommit();
    }


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
    public Integer getMenuCount() {
        return menuCount;
    }

    public void setMenuCount(Integer menuCount) {
        this.menuCount = menuCount;
    }
    public Integer getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(Integer menuPrice) {
        this.menuPrice = menuPrice;
    }
    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }


	@Override
	public String toString() {
		return "OrderObj [orderId=" + orderId + ", customerId=" + customerId + ", customerName=" + customerName
				+ ", customerAddress=" + customerAddress + ", phoneNumber=" + phoneNumber + ", menuId=" + menuId
				+ ", menuCount=" + menuCount + ", menuPrice=" + menuPrice + ", storeId=" + storeId + ", orderStatus="
				+ orderStatus + "]";
	}

}
