package webapp.gt.com.antiguaburger.model.Dao;

import webapp.gt.com.antiguaburger.model.Entity.AlternativeEntity;

import java.util.List;

public interface IAlternativeDao {
    public AlternativeEntity getAlternative(String nameFood, String nameMenu);
}
