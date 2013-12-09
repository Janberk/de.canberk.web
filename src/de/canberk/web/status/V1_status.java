package de.canberk.web.status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.canberk.web.dao.DB_Conn;

@Path("/v1/status")
public class V1_status {

	private static final String API_VERSION = "00.01.00";
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p>Java Web Service</p>";
	}

	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<p>Version: </p>" + API_VERSION;
	}

	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseStatus() throws Exception {
		PreparedStatement query = null;
		String myString = null;
		String returnString = null;
		Connection conn = null;
		StringBuffer sb = new StringBuffer();

		try {

			conn = DB_Conn.getConnection();
			// conn = DatabaseConn.dataSourceConn().getConnection();

			query = conn.prepareStatement("select username from user");
			ResultSet rs = query.executeQuery();
			
			sb.append("<h3>Database read usernames from table:</h3> ");

			while (rs.next()) {
				myString = rs.getString("username");
				returnString = "<p>" + myString + "</p>";
				
				sb.append(returnString);
			}
			query.close();			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("Closing the connection.");
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ignore) {
				}
		}
		return sb.toString();
	}

}
