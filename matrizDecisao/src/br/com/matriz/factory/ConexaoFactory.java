package br.com.matriz.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
	private static final String USUARIO = "root";
	private static final String SENHA = "q1w2e3r4";
	private static final String URL = "jdbc:mysql://localhost:3306/matrizdecisao";
	
	public static Connection conectar() throws SQLException{
		
		// resolução de um bug na conexão, "problema de registro de driver"
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());

		Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		return conexao;
	}
	// somente para testar a conexão.
	public static void main(String[] args) {
		try{
		Connection conexao = ConexaoFactory.conectar();
		System.out.println("Conexão realizada com sucesso para usuario " + USUARIO );
		}catch(SQLException ex){
			ex.printStackTrace();
			System.out.println("Não foi possivel realizar a conexão! ERRO: " + ex);		
		}
	}
}
