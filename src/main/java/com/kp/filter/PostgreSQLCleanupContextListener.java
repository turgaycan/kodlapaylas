package com.kp.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * Created by tcan on 07/02/16.
 */
public class PostgreSQLCleanupContextListener implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostgreSQLCleanupContextListener.class);

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                LOGGER.error("An error occurred while deregistering drivers.", e);
            }
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
    }
}
