package webapp.gt.com.antiguaburger.model.Dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.org.apache.xpath.internal.operations.Or;
import webapp.gt.com.antiguaburger.model.Entity.OrderEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDao implements IOrderDao {
    ConectionDB conectionDB = ConectionDB.getInstance();
    PreparedStatement pr;
    @Override
    public boolean saveOrder(String order, String object) {


        try {
            conectionDB.openConection();
            String query = "insert into orden(idOrder,orden) values(?,?)";
            pr = (PreparedStatement) conectionDB.connection.prepareStatement(query);
            pr.setString(1,order);
            pr.setObject(2, object);
            pr.executeUpdate();

            conectionDB.closeConection();

            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(throwables);
            conectionDB.closeConection();
        }
        return false;
    }

    @Override
    public String getOrder(String idOrder) {
        String cadenaJson = "";
        try {
            conectionDB.openConection();
            String query = "select orden from orden where idOrder = "+ "'"+idOrder+"'";

            ResultSet rs = conectionDB.connection.createStatement().executeQuery(query);
            while (rs.next()) cadenaJson = rs.getString("orden");

            System.out.println(cadenaJson);
            conectionDB.closeConection();
            return cadenaJson;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println(throwables);
            conectionDB.closeConection();
            return "No se ha encontrado orden";
        }
    }
}
