package webapp.gt.com.antiguaburger.model.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import webapp.gt.com.antiguaburger.model.Dao.IUserDao;
import webapp.gt.com.antiguaburger.model.Dao.UserDao;
import webapp.gt.com.antiguaburger.model.Entity.UserEntity;

import java.io.BufferedReader;

public class UserService implements IUserService {
    @Override
    public JsonObject validationUser(BufferedReader reader) {
        Gson gson = new Gson();
        IUserDao userDao = new UserDao();
        UserEntity userLogin = gson.fromJson(reader, UserEntity.class);
        UserEntity userVali = new UserEntity();


        if(userVali.getFullName() != null){
            System.out.println(userLogin.getUserName());
            userVali = userDao.getUser(userLogin.getUserName());
            System.out.println(userVali.getFullName());

            String cadenaJson = gson.toJson(userVali);
            JsonObject jsonObject = new JsonParser().parse(cadenaJson).getAsJsonObject();

            System.out.println("Hola"+jsonObject);
            return jsonObject;
        }else {
            userVali = userDao.getUser(userLogin.getUserName());
            String cadenaJson = gson.toJson(userVali);
            JsonObject jsonObject = new JsonParser().parse(cadenaJson).getAsJsonObject();

            System.out.println(jsonObject);
            return jsonObject;
        }




    }

    @Override
    public UserEntity getNameValidation(BufferedReader data) {

        IUserDao userD = new UserDao();
        Gson gson = new Gson();

        UserEntity userLogi;
        userLogi = gson.fromJson(data, UserEntity.class);
        UserEntity userVali;

        userVali = userD.getUser(userLogi.getUserName());

        System.out.println(userVali.getUserName());
        return userVali;
    }
}
