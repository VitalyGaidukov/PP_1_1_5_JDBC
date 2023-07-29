package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydb2";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;


    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

public static SessionFactory getSessionFactory(){
        Configuration configuration = new Configuration();
    Properties properties = new Properties();
    properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
    properties.put(Environment.URL, URL);
    properties.put(Environment.USER, USER_NAME);
    properties.put(Environment.PASS, PASSWORD);
    properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
    properties.put(Environment.SHOW_SQL, "true");
    properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
    configuration.setProperties(properties);

    configuration.addAnnotatedClass(User.class);


    SessionFactory sessionFactory = configuration.addAnnotatedClass(User.class).addProperties(configuration.getProperties()).buildSessionFactory();

    return sessionFactory;

}
}
