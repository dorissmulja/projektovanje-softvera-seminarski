/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author smulj
 */
public class DbConnectionFactory {
    //kao nasa klasa Konkecija u paketu baza, kad smo vjezbali za prvi klk
    public static DbConnectionFactory instance;
    private Connection connection;

    private DbConnectionFactory() {
        try {
            //ne zelimo da se dva puta kreira konekcija pa zato if uslov
            if(connection==null || connection.isClosed()){
                String url=konfiguracija.Konfiguracija.getInstance().getProperty("url");
                String username=konfiguracija.Konfiguracija.getInstance().getProperty("username");
                String password=konfiguracija.Konfiguracija.getInstance().getProperty("password");
                System.out.println("Ucitani su: "+url+username+password);
                try {
                    connection=DriverManager.getConnection(url, username, password);
                    System.out.println("konekcija sa bazom uspjesna");
                    connection.setAutoCommit(false);
                } catch (SQLException ex) {
                    Logger.getLogger(DbConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DbConnectionFactory getInstance() {
        if(instance==null)
            instance=new DbConnectionFactory();
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
   
}
