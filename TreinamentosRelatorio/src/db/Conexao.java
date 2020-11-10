/**
 * @author marcony.souza
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author marcony.souza
 *
 */
public class Conexao { 

	private static Connection connection;
	
	static { 
		try { 
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/dbRelatorios",
					"SA", ""); 
			} catch (Exception e) { 
				e.printStackTrace(); 
				}
		} 

	public static Connection getConnection() { 
		return connection; 
		} 
	}
