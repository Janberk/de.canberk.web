package de.canberk.web.inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import de.canberk.web.dao.DB_Conn;
import de.canberk.web.util.ToJSON;

@Path("/v1/inventory")
// removed * wildcard to make this more compatible with tomcat
public class V1_inventory {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnData() throws Exception {
		PreparedStatement query = null;
		Connection conn = null;
		String returnString = null;
		Response response = null;

		try {
			conn = DB_Conn.getConnection();
			query = conn.prepareStatement("select * from user");
			ResultSet rs = query.executeQuery();

			ToJSON converter = new ToJSON();
			JSONArray json = new JSONArray();

			json = converter.toJSONArray(rs);
			query.close();

			returnString = json.toString();
			response = Response.ok(returnString).build();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}
		return response;
	}

}
