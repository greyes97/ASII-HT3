package webapp.gt.com.antiguaburger.model.Entity;

public class AddEntity {
    private int idExtra;
    private String description;
    private Float price;
    
    public AddEntity(){
    }

    public int getIdExtra() {
        return idExtra;
    }

    public void setIdExtra(int idExtra) {
        this.idExtra = idExtra;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
