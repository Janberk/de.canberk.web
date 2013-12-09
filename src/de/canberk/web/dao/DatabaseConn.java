package de.canberk.web.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DatabaseConn {

	private static DataSource dataSource = null;
	private static Context context = null;

	public static DataSource dataSourceConn() throws Exception {
		
		if (dataSource != null) {
			return dataSource;
		}

		try {
			if (context == null) {
				context = new InitialContext();
				
				dataSource = (DataSource) context.lookup("jdbc/DataSource"); // Server-ID muss in der DB definiert werden.
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataSource;
	}

}
