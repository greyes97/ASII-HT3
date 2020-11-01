package webapp.gt.com.antiguaburger.model.Service;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import webapp.gt.com.antiguaburger.model.Dao.*;
import webapp.gt.com.antiguaburger.model.Entity.MenuEntity;

import java.util.ArrayList;
import java.util.List;


public class ComboService implements IComboService {
    @Override
    public JsonObject getListAlternative(String nameMenu) {
        IMenuDao menuDao = new MenuDao();
        MenuEntity menuEntity = new MenuEntity();
        IAlternativeDao alDao = new AlternativeDao();
        IAddDao addDao = new AddDao();
        List<JsonObject> list = new ArrayList<>();
        List<String>menus = menuDao.getMenu(nameMenu);

        for (String menu : menus) {
            String jsn;
            jsn = ("{\"name\":\"" + menu + "\",\"alternative\":" + alDao.getAlternative(menu, nameMenu).getItems() + "}");
            JsonObject jsonObject = new JsonParser().parse(jsn).getAsJsonObject();
            list.add(jsonObject);
        }
        MenuEntity menDao;

        menDao = menuDao.nameMenu(nameMenu);
        menuEntity.setNameMenu(menDao.getNameMenu());
        menuEntity.setPrice(menDao.getPrice());
        menuEntity.setImg(menDao.getImg());
        menuEntity.setItemFood(list);
        menuEntity.setAdd(addDao.getAddDao(nameMenu));

        Gson gson = new Gson();
        String cadenaJson = gson.toJson(menuEntity);
        JsonObject jsonObject = new JsonParser().parse(cadenaJson).getAsJsonObject();

        return jsonObject;
    }
}
