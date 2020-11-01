package webapp.gt.com.antiguaburger.model.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import webapp.gt.com.antiguaburger.model.Dao.IOrderDao;
import webapp.gt.com.antiguaburger.model.Dao.OrderDao;
import webapp.gt.com.antiguaburger.model.Entity.OrderEntity;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class JsonFile implements IFileService {
    final int ARBITARY_SIZE = 1048;
    @Override
    public void createFile(String idOrder, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("hola json");

        createFileDao(idOrder,request);
        response.setContentType("text/plain");
        response.setHeader("Content-disposition", "attachment; "+idOrder+".json");

        try(InputStream in = request.getServletContext().getResource("/"+idOrder+".json").openStream();
            OutputStream out = response.getOutputStream()) {

            byte[] buffer = new byte[ARBITARY_SIZE];

            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
        }catch (Exception e){
            System.out.println(e);
        }


    }

    public void createFileDao(String idOrder,HttpServletRequest request){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        IOrderDao dao = new OrderDao();


        String ruta = request.getServletContext().getRealPath(idOrder+".json");
        File file = new File(ruta);
        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(dao.getOrder(idOrder), writer);
            System.out.println("arhivo creado");

        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
