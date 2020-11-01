package webapp.gt.com.antiguaburger.model.Dao;





import webapp.gt.com.antiguaburger.model.Entity.MenuEntity;

import javax.swing.text.html.parser.Entity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuDao implements IMenuDao {

    ConectionDB conectionDB = ConectionDB.getInstance();

    public List<String> getMenu(String menuName) {
        List<String>menu = new ArrayList<>();
        String name = "'"+menuName+"'";

        try {
            conectionDB.openConection();
            String query = "select f.description as combo from menufood mf inner join menus as m on \n" +
                    "m.idMenu = mf.idMenu inner join foods as f on \n" +
                    "f.idFood = mf.idFood \n" +
                    "where m.description = " + name;
            ResultSet rs = conectionDB.connection.createStatement().executeQuery(query);
            while(rs.next()){
                menu.add(rs.getString("combo"));
            }
            if(menu.isEmpty()){
                menu.add("No se ha encontrado menu");
                return menu;
            }
            return menu;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            conectionDB.closeConection();
            return menu;
        }


    }

    @Override
    public MenuEntity nameMenu(String menuName) {
       MenuEntity entity = new MenuEntity();
       String name = "'"+menuName+"'";

        try {
            conectionDB.openConection();
            String query = "select description,price,image from menus where description=" + name;
            ResultSet rs = conectionDB.connection.createStatement().executeQuery(query);
            while(rs.next()){
                entity.setNameMenu(rs.getString("description"));
                entity.setPrice(Float.parseFloat(rs.getString("price")));
                entity.setImg(rs.getString("image"));
            }
            if(entity.getNameMenu().isEmpty()){
                entity.setNameMenu(("No se ha encontrado menu"));
                return entity;
            }
            return entity;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            conectionDB.closeConection();
            return entity;
        }
    }

    @Override
    public List<MenuEntity> getMenus() {

        List<MenuEntity> list = new ArrayList<>();

        try {
            conectionDB.openConection();
            String query = "select description,price,image from menus";
            ResultSet rs = conectionDB.connection.createStatement().executeQuery(query);
            while(rs.next()){
                MenuEntity entity = new MenuEntity();
                entity.setNameMenu(rs.getString("description"));
                entity.setPrice(Float.parseFloat(rs.getString("price")));
                entity.setImg(rs.getString("image"));
                list.add(entity);
            }
            if(list.get(0).getNameMenu().isEmpty()){
                System.out.println("no se ha encontrado el menu");
                return list;
            }

            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            conectionDB.closeConection();
            return list;
        }
    }

    @Override
    public int getPrecioMenu(String whatMenu) {
        switch (whatMenu) {
            case "Desayuno 1":
                return 25;
            case "Desayuno 2":
                return 18;
            case "Desayuno 3":
                return 20;
            case "Almuerzo 1":
                return 30;
            case "Almuerzo 2":
                return 35;
            case "Cena 1":
                return 28;
            default:
                return 0;
        }
    }
}
