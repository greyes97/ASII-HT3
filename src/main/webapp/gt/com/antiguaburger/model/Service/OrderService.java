package webapp.gt.com.antiguaburger.model.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.org.apache.xpath.internal.operations.Or;
import webapp.gt.com.antiguaburger.model.Dao.IOrderDao;
import webapp.gt.com.antiguaburger.model.Dao.OrderDao;
import webapp.gt.com.antiguaburger.model.Entity.OrderEntity;

import java.io.BufferedReader;

public class OrderService implements IOrderService {
    @Override
    public JsonObject saveOrderService(BufferedReader render,String userName) {
        Gson gson = new Gson();

        OrderEntity entity = gson.fromJson(render, OrderEntity.class);
        entity.setCashier(userName);
        String cadenaJson = gson.toJson(entity);
        JsonObject jsonObject = new JsonParser().parse(cadenaJson).getAsJsonObject();

        IOrderDao dao = new OrderDao();

        System.out.println("Hola json "+jsonObject);

        if(dao.saveOrder(entity.getOrder(), cadenaJson)){
            System.out.println("exito");
        }else {
            System.out.println("error");
        }


        System.out.println(jsonObject);

        return jsonObject;
    }

    @Override
    public JsonObject getOrderService(String idOrden) {

        IOrderDao dao = new OrderDao();
        return new JsonParser().parse(dao.getOrder(idOrden)).getAsJsonObject();

    }
}
