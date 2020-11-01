package webapp.gt.com.antiguaburger.model.Service;

import webapp.gt.com.antiguaburger.model.Entity.OrderEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IFileService {
    public void createFile(String idOrder, HttpServletRequest request, HttpServletResponse response) throws IOException;
}
