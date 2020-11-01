package webapp.gt.com.antiguaburger.model.Dao;


import webapp.gt.com.antiguaburger.model.Entity.MenuEntity;

import java.util.List;

public interface IMenuDao {
    public List<String> getMenu(String menuName);
    public MenuEntity nameMenu(String menuName);
    public List<MenuEntity> getMenus();
    public int getPrecioMenu(String whatMenu);
}
