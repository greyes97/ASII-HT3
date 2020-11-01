package webapp.gt.com.antiguaburger.model.Dao;

import webapp.gt.com.antiguaburger.model.Entity.AddEntity;

import java.util.List;

public interface IAddDao {
    public List<AddEntity> getAddDao(String nameMenu);
}
