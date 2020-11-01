package webapp.gt.com.antiguaburger.controller;


import webapp.gt.com.antiguaburger.model.Dao.IMenuDao;
import webapp.gt.com.antiguaburger.model.Dao.MenuDao;
import webapp.gt.com.antiguaburger.model.Service.IMenuService;
import webapp.gt.com.antiguaburger.model.Service.MenuService;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "menus", urlPatterns = {"/menus"})
public class MenuController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try(PrintWriter out = response.getWriter()){
            response.setContentType("application/json");
            IMenuService service = new MenuService();
            out.println(service.getMenus());
        }
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try(PrintWriter out = response.getWriter()){
            response.setContentType("application/json");
            out.println(request.getSession(true).getAttribute("userName"));
        }
    }


    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:9000");
        resp.setHeader("Access-Control-Allow-Methods", "GET");
    }
}
