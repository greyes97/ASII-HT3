package webapp.gt.com.antiguaburger.model.Service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.opencsv.CSVWriter;
import webapp.gt.com.antiguaburger.model.Dao.IOrderDao;
import webapp.gt.com.antiguaburger.model.Dao.OrderDao;
import webapp.gt.com.antiguaburger.model.Entity.OrderEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class CsvFile implements IFileService {
    final int ARBITARY_SIZE = 1048;
    @Override
    public void createFile(String idOrder, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("hola csv");
        createFileDao(idOrder,request);
        response.setContentType("text/plain");
        response.setHeader("Content-disposition", "attachment; "+idOrder+".csv");

        try(InputStream in = request.getServletContext().getResource("/"+idOrder+".csv").openStream();
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
        IOrderDao dao = new OrderDao();
        Gson gson = new Gson();
        String path = request.getServletContext().getRealPath(idOrder+".csv");
        JsonObject jsonObject = new JsonParser().parse(dao.getOrder(idOrder)).getAsJsonObject();
        OrderEntity entity = gson.fromJson(jsonObject,OrderEntity.class);

        StringBuilder data = new StringBuilder();
        data.append("order,").append(entity.getOrder()).append(",");
        data.append("cajero,").append(entity.getCashier()).append(",");
        data.append("fecha/hora,").append(entity.getDate()).append(",");
        data.append("cliente,").append(entity.getCustomer()).append(",");
        data.append("nit,").append(entity.getTaxId()).append(",");
        data.append("menu,").append(entity.getNameMenu()).append(",");
        for(String items : entity.getItem()){
            data.append(items).append(",");
        }
        int count = entity.getExtras().size();
        int i =0;
        for(String adds : entity.getExtras()){
            i++;
            if(i==count){
                data.append(adds);
            }else{
                data.append(adds).append(",");
            }

        }

        try{
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            try(BufferedWriter bw = new BufferedWriter(fw)){
                bw.write(String.valueOf(data));
            }catch (IOException e){
                System.out.println(e);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
