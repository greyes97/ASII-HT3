package webapp.gt.com.antiguaburger.model.Service;

import com.google.gson.JsonObject;
import webapp.gt.com.antiguaburger.model.Entity.UserEntity;

import java.io.BufferedReader;

public interface IUserService {
    public JsonObject validationUser(BufferedReader reader);
    public UserEntity getNameValidation(BufferedReader data);
}
