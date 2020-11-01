package webapp.gt.com.antiguaburger.model.Service;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import webapp.gt.com.antiguaburger.model.Dao.IMenuDao;
import webapp.gt.com.antiguaburger.model.Dao.MenuDao;

public class MenuService implements IMenuService {
    @Override
    public String getMenus() {
        IMenuDao dao = new MenuDao();
        Gson gson= new Gson();
        String cadenaJson = gson.toJson(dao.getMenus());
        return cadenaJson;
    }
}
