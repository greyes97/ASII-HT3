package webapp.gt.com.antiguaburger.model.Dao;

import webapp.gt.com.antiguaburger.model.Entity.UserEntity;

public interface IUserDao {
    public UserEntity getUser(String userName);
}
