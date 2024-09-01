import java.sql.*;


class executionQueries {
	
	private String uname = "root";
	private String pass = "garethbale11";
	private String url = "jdbc:mysql://localhost:3306/jdbcdemo";
	private Connection connection;
	
	public executionQueries() {
		try {
			this.connection = DriverManager.getConnection(url, uname, pass);
			System.out.println("Connection Established Successfully");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int InsertIntoTable(int id, String name, int age) {
		String query = "insert into demo (id, name, age) value (?, ?, ?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.setString(2, name);
			preparedStatement.setInt(3,  age);
			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public ResultSet getData() {
		String query = "select * from demo";
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			return rs;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public int updateTableValue(String older, String update) {
		String query = "update demo set name = ? where name = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, update);
			preparedStatement.setString(2, older);
			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public int deleteFromTable(String name) {
		String query = "delete from demo where name = ?";
		try {			
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, name);
			
			int rowsAffected = preparedStatement.executeUpdate();
			return rowsAffected;
		}
		catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
}


public class JDBCDemo {
	public static void main(String args[]) throws Exception{
		
		executionQueries eq = new executionQueries();
		int rowsAffected = eq.updateTableValue("Rohan", "Alan");
		System.out.println(rowsAffected + " row(s) affected");
		
		ResultSet rs = eq.getData();
		while (rs.next()) {
			System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3));
		}
	}
}
