package webapp.gt.com.antiguaburger.model.Dao;

import webapp.gt.com.antiguaburger.model.Entity.UserEntity;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class UserDao implements IUserDao {
    @Override
    public UserEntity getUser(String userName) {
        ConectionDB conectionDB = ConectionDB.getInstance();
        UserEntity user = new UserEntity();

        try {
            conectionDB.openConection();
            String query = "select * from users where userName= "+"'"+userName+"'";
            ResultSet rs = conectionDB.connection.createStatement().executeQuery(query);
            while (rs.next()){
                user.setIdUser(Integer.parseInt(rs.getString("idUser")));
                user.setUserName(rs.getString("userName"));
                user.setFullName(rs.getString("fullName"));
                user.setSurName(rs.getString("surName"));
                Date date = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(rs.getString("dateBirth"));
                user.setDateBirth(date);
            }

        } catch (SQLException | ParseException throwables) {
            throwables.printStackTrace();
            conectionDB.closeConection();
            return user;
        }

        if(user.getIdUser() == 0){
            user.setSurName("No existe usuario");
            user.setUserName("No existe usuario");
            user.setFullName("No existe usuario");
            return user;
        }
        return user;
    }
}
