package webapp.gt.com.antiguaburger.model.Service;

import com.google.gson.JsonObject;

import java.io.BufferedReader;

public interface IOrderService {
    public JsonObject saveOrderService(BufferedReader render, String userName);
    public JsonObject getOrderService(String idOrden);
}
