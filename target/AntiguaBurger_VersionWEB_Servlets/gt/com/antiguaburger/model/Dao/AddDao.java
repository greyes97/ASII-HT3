package webapp.gt.com.antiguaburger.model.Dao;

import webapp.gt.com.antiguaburger.model.Entity.AddEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddDao implements IAddDao {
    @Override
    public List<AddEntity> getAddDao(String nameMenu) {

        ConectionDB conectionDB = ConectionDB.getInstance();
        List<AddEntity>list = new ArrayList<>();

        try {
            conectionDB.openConection();
            String query = "select ex.idExtra,ex.description, ex.price from extrasmenu em inner join menus as m on\n" +
                    "m.idMenu = em.idMenu inner join extras as ex on \n" +
                    "ex.idExtra = em.idExtra\n" +
                    "where m.description = "+"'"+nameMenu+"'";

            ResultSet rs = conectionDB.connection.createStatement().executeQuery(query);
            while (rs.next()){
                AddEntity entity = new AddEntity();
                entity.setIdExtra(Integer.parseInt(rs.getString("idExtra")));
                entity.setDescription(rs.getString("description"));
                entity.setPrice(Float.parseFloat(rs.getString("price")));
                list.add(entity);
            }
            conectionDB.closeConection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            conectionDB.closeConection();
            AddEntity entity = new AddEntity();
            entity.setDescription("No se ha encotrado los extras");
            entity.setPrice(Float.parseFloat("0"));
            list.add(entity);
            conectionDB.closeConection();
            return list;
        }
        conectionDB.closeConection();
        return list;
    }
}
