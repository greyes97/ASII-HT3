package webapp.gt.com.antiguaburger.model.Entity;

import com.google.gson.JsonObject;

import java.util.List;

public class MenuEntity {
    private String nameMenu;
    private float price;
    private String img;
    private List<JsonObject> itemFood;
    private List<AddEntity> add;

    public List<AddEntity> getAdd() {
        return add;
    }

    public void setAdd(List<AddEntity> add) {
        this.add = add;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNameMenu() {
        return nameMenu;
    }

    public void setNameMenu(String nameMenu) {
        this.nameMenu = nameMenu;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<JsonObject> getItemFood() {
        return itemFood;
    }

    public void setItemFood(List<JsonObject> itemFood) {
        this.itemFood = itemFood;
    }
}
