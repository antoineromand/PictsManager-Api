package com.epitech.pictmanager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class DatabaseTest {

    @Autowired
    private DataSource dataSource;
    @Test
    public void connectToDatabase() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.isValid(10);
        connection.close();
    }
}
