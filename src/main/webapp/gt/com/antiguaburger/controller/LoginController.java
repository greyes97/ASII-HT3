package webapp.gt.com.antiguaburger.controller;


import com.google.gson.JsonObject;

import webapp.gt.com.antiguaburger.model.Service.IMenuService;
import webapp.gt.com.antiguaburger.model.Service.IUserService;
import webapp.gt.com.antiguaburger.model.Service.MenuService;
import webapp.gt.com.antiguaburger.model.Service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "login", urlPatterns = {"/login"})
public class LoginController extends HttpServlet{


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try(PrintWriter out = response.getWriter()){
            response.setContentType("application/json");
        }
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        IUserService service = new UserService();
        BufferedReader reader = request.getReader();


        try(PrintWriter out = response.getWriter()){

            JsonObject json = service.validationUser(reader);

            String userName = json.get("userName").getAsString();
            String fullName = json.get("fullName").getAsString()+" "+json.get("surName").getAsString();

            request.getSession(true).setAttribute("userName",userName);
            request.getSession(true).setAttribute("fullName", fullName);

            out.println(json);



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
