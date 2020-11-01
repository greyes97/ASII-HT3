package webapp.gt.com.antiguaburger.controller;


import com.google.gson.Gson;
import com.google.gson.JsonObject;

import com.google.gson.JsonParser;
import org.json.JSONObject;
import org.json.JSONTokener;
import webapp.gt.com.antiguaburger.model.Service.*;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "download", urlPatterns = {"/download"})
public class DownloadController extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        FileFactoryService serviceFactory = new FileFactoryService();
        IFileService file = serviceFactory.getFile("json");
        file.createFile(request.getParameter("id"),request,response);

    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Gson gson = new Gson();
        BufferedReader reader = request.getReader();
        JSONTokener token = new JSONTokener(reader);
        JSONObject jsonObjectt = new JSONObject(token);
        JsonObject jsonObject = new JsonParser().parse(gson.toJson(jsonObjectt)).getAsJsonObject();
        String cadJson = jsonObject.get("map").toString();
        JsonObject json = new JsonParser().parse(cadJson).getAsJsonObject();

        FileFactoryService serviceFactory = new FileFactoryService();
        IFileService file = serviceFactory.getFile(json.get("option").getAsString());
        file.createFile(json.get("order").getAsString(),request,response);
    }



    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "http://localhost:9000");
        resp.setHeader("Access-Control-Allow-Methods", "GET");
    }
}
