package webapp.gt.com.antiguaburger.model.Service;

import com.google.gson.JsonObject;
import webapp.gt.com.antiguaburger.model.Entity.AlternativeEntity;
import org.json.JSONObject;

import java.util.List;

public interface IComboService {
    public JsonObject getListAlternative(String nameMenu);
}
