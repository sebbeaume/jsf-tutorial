package com.mycompany.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.mycompany.app.model.User;

import lombok.Getter;
import lombok.Setter;

@ManagedBean(name = "navigationController", eager = true)
@RequestScoped
public class NavigationController {

    @ManagedProperty(value = "#{user}")
    @Getter
    @Setter
    private User user;

    public NavigationController() {
        createUserTable();
    }

    private void createUserTable() {

        String createUserStatement =
                "CREATE TABLE IF NOT EXISTS \"BANANA\"( \"USERNAME\"  VARCHAR(20) NOT NULL, \"PASSWORD\" VARCHAR(20) NOT NULL)";

        executeStatement(createUserStatement);

    }

    public void createUser() {

        executeStatement(sqlStatement);
    }

    private Connection getConnection() throws SQLException, ClassNotFoundException {

        Class.forName("org.h2.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        System.out.println("Connection completed.");
        return conn;

    }


    private void executeStatement(String createUserStatement) {
        try (Connection connection = getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(createUserStatement)) {
                preparedStatement.executeUpdate();
                connection.commit();
            }
            System.out.println("Transaction commited");
        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
    }

}
