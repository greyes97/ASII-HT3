package webapp.gt.com.antiguaburger.model.Entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;
import java.util.List;

@XmlRootElement(name = "order")
public class OrderEntity {
    public OrderEntity(){
    }

    private String order;
    private String cashier;
    private String date;
    private String customer;
    private String taxId;
    private String nameMenu;
    private List<String> item;
    private List<String>extras;
    private int total;

    public String getOrder() {
        return order;
    }

    @XmlAttribute(name = "id")
    public void setOrder(String order) {
        this.order = order;
    }


    public String getDate() {
        return date;
    }

    public String getCashier() {
        return cashier;
    }

    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getTaxId() {
        return taxId;
    }


    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getNameMenu() {
        return nameMenu;
    }

    public void setNameMenu(String nameMenu) {
        this.nameMenu = nameMenu;
    }

    public List<String> getItem() {
        return item;
    }

    public void setItem(List<String> item) {
        this.item = item;
    }

    public List<String> getExtras() {
        return extras;
    }

    public void setExtras(List<String> extras) {
        this.extras = extras;
    }
}
