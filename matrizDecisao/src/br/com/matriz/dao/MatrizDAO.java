package br.com.matriz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import br.com.matriz.domain.Matriz;
import br.com.matriz.domain.Produtos;
import br.com.matriz.factory.ConexaoFactory;


public class MatrizDAO {

	public void salvar(Matriz m) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO matriz ");
		sql.append("(atividade, tarefa, dtInclusao, dtConclusao, dtPrazo, criticidade, tipo, produtos_codigo) ");
		sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?) ");

		// conecta e guarda a conexão aberta nessa variavel.
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		//Date d = new Date(d.getTime());
		
		comando.setString(1, m.getAtividade());
		comando.setString(2, m.getTarefa());
		comando.setDate(3, new java.sql.Date(m.getDtInclusao().getTime()));
		comando.setDate(4, new java.sql.Date(m.getDtConclusao().getTime()));
		comando.setDate(5, new java.sql.Date(m.getDtPrazo().getTime()));;
		comando.setString(6, m.getCriticidade());
		comando.setString(7, m.getTipo());
		comando.setLong(8, m.getProdutos().getCodigo());
		comando.executeUpdate();
 
	}

	
	public ArrayList<Matriz> listar() throws SQLException {

		// produtos = fabricante;
		// matriz   = produto; 
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT m.codigo, m.atividade, m.tarefa, m.dtInclusao, m.dtConclusao, m.dtPrazo, m.criticidade, m.tipo ,p.codigo, p.produto ");
		sql.append("FROM matriz m ");
		sql.append("INNER JOIN produtos p ON p.codigo = m.produtos_codigo ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Matriz> itens = new ArrayList<Matriz>();
		GregorianCalendar gc = new GregorianCalendar();
		
		while (resultado.next()) {
			
			Produtos p = new Produtos();
			p.setCodigo(resultado.getLong("p.codigo"));
			p.setProduto(resultado.getString("p.produto"));
			
			Matriz m = new Matriz();
			
			m.setCodigo(resultado.getLong("m.codigo"));
			m.setAtividade(resultado.getString("m.atividade"));
			m.setTarefa(resultado.getString("m.tarefa"));
			
	
			gc.setTime(resultado.getDate("m.dtInclusao"));	
			m.setDtInclusao(11, 11, 2016);
			
			
			gc.setTime(resultado.getDate("m.dtConclusao"));	
			m.setDtConclusao(11, 11, 2016);
			
			gc.setTime(resultado.getDate("m.dtprazo"));	
			m.setDtPrazo(11, 11, 2016);
	
			m.setCriticidade(resultado.getString("m.criticidade"));
			m.setTipo(resultado.getString("m.tipo"));
			m.setProdutos(p);	
			
			itens.add(m);
		}

		return itens;
	}

	public void excluir(Matriz m) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM matriz ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setLong(1, m.getCodigo());

		comando.executeUpdate();

	}

	
	public void editar(Matriz m) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE matriz ");
		sql.append("SET atividade = ?, tarefa = ?, dtInclusao = ?, dtConclusao = ?, dtPrazo = ?, criticidade = ?, tipo = ?,  produtos_codigo = ? ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, m.getAtividade());
		comando.setString(2, m.getTarefa());
		comando.setDate(3, new java.sql.Date(m.getDtInclusao().getTime()));
		comando.setDate(4, new java.sql.Date(m.getDtConclusao().getTime()));
		comando.setDate(5, new java.sql.Date(m.getDtPrazo().getTime()));;
		comando.setString(6, m.getCriticidade());
		comando.setString(7, m.getTipo());
		comando.setLong(8, m.getProdutos().getCodigo());
		comando.setLong(9, m.getCodigo());
		comando.executeUpdate();
	} 

}