package net.javaguides.login.database;

import net.javaguides.login.bean.LoginBean;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {
	
	public boolean validate(LoginBean loginBean) throws ClassNotFoundException {
		boolean status = false;
		
		PoolProperties p = getPoolProperties();
		final DataSource dataSource = getDataSource(p);
		
		try (Connection connection = dataSource.getConnection()) {
			
			PreparedStatement preparedStatement = connection.prepareStatement(
					"select * from login where username = ? and password = ? ");
			preparedStatement.setString(1, loginBean.getUsername());
			preparedStatement.setString(2, loginBean.getPassword());
			
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			status = rs.next();
			
			rs.close();
			preparedStatement.close();
			
		} catch (SQLException e) {
			printSQLException(e);
			
		}
		
		return status;
	}
	
	private DataSource getDataSource(final PoolProperties p) {
		DataSource datasource = new DataSource();
		datasource.setPoolProperties(p);
		return datasource;
	}
	
	private PoolProperties getPoolProperties() {
		PoolProperties p = new PoolProperties();
		p.setUrl("jdbc:postgresql://db:5432/db");
		p.setDriverClassName("org.postgresql.Driver");
		p.setUsername("user");
		p.setPassword("password");
		p.setJmxEnabled(true);
		p.setTestWhileIdle(false);
		p.setTestOnBorrow(true);
		p.setValidationQuery("SELECT 1");
		p.setTestOnReturn(false);
		p.setValidationInterval(30000);
		p.setTimeBetweenEvictionRunsMillis(30000);
		p.setMaxActive(100);
		p.setInitialSize(10);
		p.setMaxWait(10000);
		p.setRemoveAbandonedTimeout(60);
		p.setMinEvictableIdleTimeMillis(30000);
		p.setMinIdle(10);
		p.setLogAbandoned(true);
		p.setRemoveAbandoned(true);
		p.setJdbcInterceptors(
				"org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;" +
				"org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
		return p;
	}
	
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}