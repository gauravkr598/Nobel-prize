package search.api;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class Search {
	
	private Connection con;
	
	public Search(Connection con) {
		this.con = con;
	}
	//API for Search by year
	public String searchByYear(String years) throws Exception {
		JSONObject objOut = new JSONObject();
		JSONObject obj = null;
		JSONArray objList = new JSONArray();
		if(years==null){
			objOut.put("message", "please provise valid year");
			objOut.put("status","failed");
			return objOut.toString();
		}
		int cnt = 0;
		try {
			String query="select  * from  prize a,laureates b WHERE  a.p_id =b.p_id and year= ?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, years);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				cnt++;
				obj = new JSONObject();
				obj.put("id", rs.getString("id"));
				obj.put("firstname", rs.getString("firstname"));
				obj.put("surname", rs.getString("surname"));
				obj.put("motivation", rs.getString("motivation"));
				obj.put("share", rs.getString("share"));
				obj.put("year", rs.getString("year"));
				obj.put("category",rs.getString("category"));
				obj.put("overallMotivation", rs.getString("overallMotivation"));
				objList.put(obj);
			}
			if(cnt != 0) {
				objOut.put("status", "success");
				objOut.put("message", "Data fetched");
				objOut.put("result_list", objList);
			} else {
				objOut.put("status", "failed");
				objOut.put("message", "Data is not available for this year");
			}
		} catch (Exception e) {
			e.printStackTrace();
			objOut.put("status", "failed");
			objOut.putOpt("message", "something went wrong exp: "+e.toString());
		}
		return objOut.toString();
	}
	//Search Category 
	public JSONArray getAllCategory(){
		JSONArray list=new JSONArray();
		String query="select distinct category from prize";
		try {
			PreparedStatement ps=this.con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				String category=rs.getString("category");
				list.put(category);
				System.out.println(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//Search By Year And Category
	public String searchByYearAndCategory(String years,String categories) throws JSONException {
		JSONObject objOut = new JSONObject();
		JSONObject obj = null;
		JSONArray objList = new JSONArray();
		if(years==null || categories==null){
			objOut.put("message", "please provise valid year and category");
			objOut.put("status","failed");
			return objOut.toString();
		}
		int cnt = 0;
		try {
			String query="select  * from  prize a,laureates b WHERE  a.p_id =b.p_id and year = ? and category = ?";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, years);
			ps.setString(2, categories);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				cnt++;
				obj = new JSONObject();
				obj.put("id", rs.getString("id"));
				obj.put("firstname", rs.getString("firstname"));
				obj.put("surname", rs.getString("surname"));
				obj.put("motivation", rs.getString("motivation"));
				obj.put("share", rs.getString("share"));
				obj.put("year", rs.getString("year"));
				obj.put("category",rs.getString("category"));
				obj.put("overallMotivation", rs.getString("overallMotivation"));
				objList.put(obj);
			}
			if(cnt != 0) {
				objOut.put("status", "success");
				objOut.put("message", "Data fetched");
				objOut.put("result_list", objList);
			} else {
				objOut.put("status", "failed");
				objOut.put("message", "Data is not available for this year and category");
			}
		}catch (Exception e) {
			e.printStackTrace();
			objOut.put("status", "failed");
			objOut.putOpt("message", "something went wrong exp: "+e.toString());
		}
		return objOut.toString();
	}
	//search By name
		public String searchByName(String firstname,String surname) throws JSONException {
			JSONObject objOut = new JSONObject();
			JSONObject obj = null; 
			JSONArray objList = new JSONArray();
			if(firstname==null || surname==null){
				objOut.put("message", "please provise valid year and category");
				objOut.put("status","failed");
				return objOut.toString();
			}
			int cnt = 0;
			try {
				String query="select  * from  prize a,laureates b WHERE  a.p_id =b.p_id and firstname = ? and surname = ?";
				PreparedStatement ps=con.prepareStatement(query);
				ps.setString(1, firstname);
				ps.setString(2, surname);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					cnt++;
					obj = new JSONObject();
					obj.put("id", rs.getString("id"));
					obj.put("firstname", rs.getString("firstname"));
					obj.put("surname", rs.getString("surname"));
					obj.put("motivation", rs.getString("motivation"));
					obj.put("share", rs.getString("share"));
					obj.put("year", rs.getString("year"));
					obj.put("category",rs.getString("category"));
					obj.put("overallMotivation", rs.getString("overallMotivation"));
					objList.put(obj);
					System.out.println(obj.toString());
				}
				if(cnt != 0) {
					objOut.put("status", "success");
					objOut.put("message", "Data fetched");
					objOut.put("result_list", objList);
				} else {
					objOut.put("status", "failed");
					objOut.put("message", "Data is not available for this year and category");
				}
			}catch (Exception e) {
				e.printStackTrace();
				objOut.put("status", "failed");
				objOut.putOpt("message", "something went wrong exp: "+e.toString());
			}
			return objOut.toString();
		}
		//Search All winner 
		public JSONArray getAllWinner() throws Exception{
			JSONArray list=new JSONArray();
			JSONObject obj=null;
			String query="select  * from  prize a,laureates b WHERE  a.p_id =b.p_id order by firstname,surname";
			try {
				PreparedStatement ps=this.con.prepareStatement(query);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					obj = new JSONObject();
					obj.put("id", rs.getString("id"));
					obj.put("firstname", rs.getString("firstname"));
					obj.put("surname", rs.getString("surname"));
					obj.put("motivation", rs.getString("motivation"));
					obj.put("share", rs.getString("share"));
					obj.put("year", rs.getString("year"));
					obj.put("category",rs.getString("category"));
					obj.put("overallMotivation", rs.getString("overallMotivation"));
					list.put(obj);
					//System.out.println(obj);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
}