package it.polito.tdp.food.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.food.model.AdiacenzeCibo;
import it.polito.tdp.food.model.Condiment;
import it.polito.tdp.food.model.Food;
import it.polito.tdp.food.model.Portion;
import java.util.*;
public class FoodDAO {
	public List<Food> listAllFoods(){
		String sql = "SELECT * FROM food" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Food> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Food(res.getInt("food_code"),
							res.getString("display_name")
							));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}

	}
	
	public List<Condiment> listAllCondiments(){
		String sql = "SELECT * FROM condiment" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Condiment> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Condiment(res.getInt("condiment_code"),
							res.getString("display_name"),
							res.getDouble("condiment_calories"), 
							res.getDouble("condiment_saturated_fats")
							));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}
	}
	
	public List<Portion> listAllPortions(){
		String sql = "SELECT * FROM portion" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Portion> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Portion(res.getInt("portion_id"),
							res.getDouble("portion_amount"),
							res.getString("portion_display_name"), 
							res.getDouble("calories"),
							res.getDouble("saturated_fats"),
							res.getInt("food_code")
							));
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}

	}
	public Map<Integer, Food> getVertex(Integer porzione, Map<Integer,Food> idMap){
		String sql = "SELECT distinct f.food_code AS id, f.display_name AS nome, COUNT(p.portion_id)AS ctn "
				+ "FROM food f, portion p "
				+ "WHERE p.food_code = f.food_code "
				+ "GROUP BY f.food_code HAVING COUNT(ctn)<=? "
				+ "ORDER BY f.display_name ASC ";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, porzione);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Food f= new Food(rs.getInt("id"), rs.getString("nome"));
				idMap.put(f.getFood_code(),f);
			}
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return idMap;
	}
	
	public List<AdiacenzeCibo> getEdges(Map<Integer,Food> idMap){
		List<AdiacenzeCibo> adiacenze = new LinkedList<AdiacenzeCibo>();
		String sql = "SELECT f1.food_code AS v1,f2.food_code AS v2, AVG(c.condiment_calories) AS peso "
				+ "FROM food_condiment f1, food_condiment f2, condiment c "
				+ "WHERE c.condiment_code = f1.condiment_code AND f1.food_code>f2.food_code AND f1.condiment_code = f2.condiment_code "
				+ "GROUP BY f1.food_code,f2.food_code ";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				if(idMap.containsKey(rs.getInt("v1"))&& idMap.containsKey(rs.getInt("v2"))) {
					Food f1 = idMap.get(rs.getInt("v1"));
					Food f2 = idMap.get(rs.getInt("v2"));
					AdiacenzeCibo a = new AdiacenzeCibo(f1, f2, rs.getDouble("peso"));
					adiacenze.add(a);
				}
				
				
			}
			conn.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return adiacenze;
	}
	
}
