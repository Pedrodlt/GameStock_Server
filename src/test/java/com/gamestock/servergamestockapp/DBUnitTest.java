/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gamestock.servergamestockapp;

import java.io.InputStream;
import java.sql.SQLException;
import org.dbunit.DatabaseUnitException;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

/**
 *
 * @author pedro
 */
public abstract class DBUnitTest {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gamestockapp?serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "898989";
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

    protected IDatabaseTester databaseTester;

    public DBUnitTest() throws ClassNotFoundException, SQLException, DatabaseUnitException {
        databaseTester = new JdbcDatabaseTester(DRIVER_CLASS, DB_URL, DB_USER, DB_PASSWORD);
    }

    protected void setUpDatabase(String dataSetFile) throws Exception {
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(dataSetFile);
        
        if (resourceAsStream == null) {
            throw new IllegalArgumentException("El archivo " + dataSetFile + " no se encuentra en src/test/resources.");
        }
        
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(resourceAsStream);
        databaseTester.setDataSet(dataSet);
        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
        databaseTester.onSetup();
    }

    protected void tearDownDatabase() throws Exception {
        databaseTester.setTearDownOperation(DatabaseOperation.DELETE);
        databaseTester.onTearDown();
    }
}
