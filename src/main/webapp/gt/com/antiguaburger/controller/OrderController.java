package webapp.gt.com.antiguaburger.controller;
import com.google.gson.JsonObject;
import webapp.gt.com.antiguaburger.model.Dao.IOrderDao;
import webapp.gt.com.antiguaburger.model.Service.ComboService;
import webapp.gt.com.antiguaburger.model.Service.IComboService;
import webapp.gt.com.antiguaburger.model.Service.IOrderService;
import webapp.gt.com.antiguaburger.model.Service.OrderService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "order", urlPatterns = {"/order"})
public class OrderController extends HttpServlet {
    private JsonObject jsonObject;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws  IOException {
        try(PrintWriter out = response.getWriter()){
            response.setContentType("application/json");
            IOrderService service = new OrderService();
            out.println(service.getOrderService(request.getParameter("order")));
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        IOrderService service = new OrderService();
        BufferedReader reader = request.getReader();
        jsonObject = service.saveOrderService(reader, request.getSession(true).getAttribute("userName").toString());

        try(PrintWriter out = response.getWriter()){
            out.println(jsonObject);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:9000");
        resp.setHeader("Access-Control-Allow-Methods", "GET");
    }
}
