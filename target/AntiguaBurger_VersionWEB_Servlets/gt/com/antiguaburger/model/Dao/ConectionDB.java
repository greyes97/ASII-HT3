package webapp.gt.com.antiguaburger.model.Dao;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectionDB {
    public static ConectionDB conectionDB;
    public Connection connection;

    private ConectionDB(){

    }
    public static ConectionDB getInstance(){
        if(conectionDB == null){
            conectionDB = new ConectionDB();
        }
        return conectionDB;
    }

    public void openConection() {

        try {
            InitialContext context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("jdbc/antiguaBurgerDB");
            connection = dataSource.getConnection();
            System.out.println("Conexion eexitosa");
        } catch (SQLException | NamingException e) {
            //System.out.println(e);
            closeConection();
        }

    }

    public void closeConection(){
        try{
            connection.close();
            System.out.println("Cerrando conexion");
        } catch(SQLException ex){
            System.out.println("error");
        }
    }
}
