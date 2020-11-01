package webapp.gt.com.antiguaburger.controller;

import webapp.gt.com.antiguaburger.model.Service.ComboService;
import webapp.gt.com.antiguaburger.model.Service.IComboService;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "add", urlPatterns = {"/add"})
public class AddController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws  IOException {
        try(PrintWriter out = response.getWriter()){
            response.setContentType("application/json");
            IComboService service = new ComboService();
            out.println(service.getListAlternative(request.getParameter("id")));
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try(PrintWriter out = response.getWriter()){
            out.println(request.getSession(true).getAttribute("fullName"));
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
