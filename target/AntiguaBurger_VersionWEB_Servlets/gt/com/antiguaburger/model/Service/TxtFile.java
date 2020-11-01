package webapp.gt.com.antiguaburger.model.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import webapp.gt.com.antiguaburger.model.Dao.IOrderDao;
import webapp.gt.com.antiguaburger.model.Dao.OrderDao;
import webapp.gt.com.antiguaburger.model.Entity.OrderEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class TxtFile implements IFileService {
    final int ARBITARY_SIZE = 1048;
    @Override
    public void createFile(String idOrder, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("hola txt");
        createFileDao(idOrder,request);

        response.setContentType("text/plain");
        response.setHeader("Content-disposition", "attachment; "+idOrder+".txt");

        try(InputStream in = request.getServletContext().getResource("/"+idOrder+".txt").openStream();
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

    public void createFileDao(String idOrder, HttpServletRequest request){
        IOrderDao dao = new OrderDao();
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonParser().parse(dao.getOrder(idOrder)).getAsJsonObject();
        String path = request.getServletContext().getRealPath(idOrder+".txt");

        OrderEntity orderEntity = gson.fromJson(jsonObject,OrderEntity.class);

        StringBuilder cadena = new StringBuilder();

        cadena.append("-----------------------------------------------------------\n");
        cadena.append("-----------------------------------------------------------\n");
        cadena.append("ORDER: ").append(orderEntity.getOrder()).append("\n");
        cadena.append("CAJERO: ").append(orderEntity.getCashier()).append("\n");
        cadena.append("FECHA/HORA: ").append(orderEntity.getDate()).append("\n");
        cadena.append("CLIENTE: ").append(orderEntity.getCustomer()).append("\n");
        cadena.append("NIT: ").append(orderEntity.getTaxId()).append("\n");
        cadena.append("-----------------------------------------------------------\n");
        cadena.append("MENU: ").append(orderEntity.getNameMenu()).append("\n");

        for(String items : orderEntity.getItem()){
            cadena.append(items).append("\n");
        }
        cadena.append("-----------------------------------------------------------\n");
        cadena.append("Extras\n");

        for (String adds : orderEntity.getExtras()){
            cadena.append(adds).append("\n");
        }
        cadena.append("\tTOTAL: ").append(orderEntity.getTotal()).append("\n");
        cadena.append("-----------------------------------------------------------\n");
        cadena.append("-----------------------------------------------------------\n");

        try{
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            try(BufferedWriter bw = new BufferedWriter(fw)){
                bw.write(String.valueOf(cadena));
            }catch (IOException e){
                System.out.println(e);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
