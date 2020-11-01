package webapp.gt.com.antiguaburger.model.Dao;

import com.google.gson.JsonObject;
import webapp.gt.com.antiguaburger.model.Entity.OrderEntity;

public interface IOrderDao {
    public boolean saveOrder(String order, String object);
    public String getOrder(String idOrder);
}
