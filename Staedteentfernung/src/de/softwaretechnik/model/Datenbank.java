package de.softwaretechnik.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public final class Datenbank {
	
	private static Datenbank _INSTANCE;
	
    private Connection _conn;
    private String _url = "jdbc:mysql://localhost:3306/";
    private String _dbName = "staedte";
    private String _userName = "root";
    private String _password = "";
	private ResultSet _rs = null;

    private Datenbank() throws SQLException{
    		this._conn = DriverManager.getConnection(_url + _dbName, _userName, _password);
    }
    
    public Connection getConnection() {
    	return _conn;
    }

    public static Datenbank getInstance() throws SQLException{
    	if (_INSTANCE == null) {
    		_INSTANCE = new Datenbank();
    	} else if (_INSTANCE.getConnection().isClosed()) {
    		_INSTANCE = new Datenbank();
    	}
    	return _INSTANCE;
    }
     
    public ResultSet getDBAusgabe(String query) {
		try {
			Statement stmt = _conn.createStatement();
			_rs = stmt.executeQuery(query);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
    	return _rs;
    }

}