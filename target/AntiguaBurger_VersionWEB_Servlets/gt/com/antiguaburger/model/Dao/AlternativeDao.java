package webapp.gt.com.antiguaburger.model.Dao;

import webapp.gt.com.antiguaburger.model.Entity.AlternativeEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlternativeDao implements IAlternativeDao {
    ConectionDB conectionDB = ConectionDB.getInstance();

    @Override
    public AlternativeEntity getAlternative(String nameFood, String nameMenu) {
        List<String>alternativeDao = new ArrayList<>();
        AlternativeEntity entity = new AlternativeEntity();

        try{
            conectionDB.openConection();
            String food = "'"+nameFood+"'";
            String name = "'"+nameMenu+"'";
            String query = "select al.description as alternative from alternative al inner join foods as fo on " +
                    "fo.idFood = al.idFood inner join menus as me on " +
                    "me.idMenu = al.idMenu " +
                    "where fo.description = "+food+" and me.description = "+name;

            ResultSet rs = conectionDB.connection.createStatement().executeQuery(query);
            while (rs.next()){
                alternativeDao.add("\""+rs.getString("alternative")+"\"");
            }
            if(alternativeDao.isEmpty()){
                alternativeDao.remove(0);
            }
            entity.setName(nameFood);
            entity.setItems(alternativeDao);
            return entity;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            conectionDB.closeConection();
            return entity;
        }
    }
}
