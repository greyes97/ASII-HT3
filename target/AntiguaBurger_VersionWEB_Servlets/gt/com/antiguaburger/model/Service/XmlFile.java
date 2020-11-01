package webapp.gt.com.antiguaburger.model.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import webapp.gt.com.antiguaburger.model.Dao.IOrderDao;
import webapp.gt.com.antiguaburger.model.Dao.OrderDao;
import webapp.gt.com.antiguaburger.model.Entity.OrderEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;

public class XmlFile implements IFileService {
    final int ARBITARY_SIZE = 1048;
    @Override
    public void createFile(String idOrder, HttpServletRequest request, HttpServletResponse response) throws IOException {

        createFileDao(idOrder,request);

        response.setContentType("text/plain");
        response.setHeader("Content-disposition", "attachment; "+idOrder+".xml");

        try(InputStream in = request.getServletContext().getResource("/"+idOrder+".xml").openStream();
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
        OrderEntity entity = gson.fromJson(dao.getOrder(idOrder),  OrderEntity.class);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(OrderEntity.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(entity, sw);
            XmlMapper xmlMapper = new XmlMapper();
            String xml = xmlMapper.writeValueAsString(entity);

            String ruta = request.getServletContext().getRealPath(idOrder+".xml");
            File file = new File(ruta);
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(xml);
                System.out.println("arhivo creado");

            } catch (IOException e) {
                System.out.println(e);
            }
        } catch (JAXBException | JsonProcessingException e) {
            e.printStackTrace();
        }


    }
}
