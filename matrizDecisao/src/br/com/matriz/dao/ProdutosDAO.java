package br.com.matriz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.matriz.domain.Produtos;
import br.com.matriz.factory.ConexaoFactory;

public class ProdutosDAO {
	// quando coloco o throws quem deverá tratar o erro obrigatoriamente será o
	// JSF.s
	public void salvar(Produtos p) throws SQLException {
		// StringBuilder melhora a forma de concatenação, ex: + "blabla"
		StringBuilder sql = new StringBuilder();
		// comando append serve para juntar varias strings.
		sql.append("INSERT INTO produtos ");
		sql.append("(produto) ");
		sql.append("VALUES (?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, p.getProduto());

		comando.executeUpdate();

	}

	public void excluir(Produtos p) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("DELETE FROM produtos ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, p.getCodigo());

		comando.executeUpdate();

	}

	// void pq não retorna nada.
	public void editar(Produtos p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE produtos ");
		sql.append("SET produto = ? ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, p.getProduto());
		comando.setLong(2, p.getCodigo());

		comando.executeUpdate();
	}

	// quando pesquisa tem que retornar algo.
	public Produtos buscarPorCodigo(Produtos p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, produto ");
		sql.append("FROM produtos ");
		sql.append("WHERE codigo = ?");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, p.getCodigo());

		ResultSet resultado = comando.executeQuery();

		Produtos retorno = null;

		if (resultado.next()) {
			retorno = new Produtos();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setProduto(resultado.getString("produto"));
		}
		return retorno;
	}

	public ArrayList<Produtos> listar() throws SQLException {
		
		//Comentados não funcionaram não sei porque!!!
		StringBuilder sql = new StringBuilder();
		
		//String sql = new String();
		
		//sql = ("SELECT codigo, descricao FROM fabricante ORDER BY codigo");
		
		
		sql.append("SELECT codigo, produto ");
		sql.append("FROM produtos ");
		sql.append("ORDER BY produto");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Produtos> lista = new ArrayList<Produtos>();

		// tem prox? se tem copia pra o objeto F, linha por linha.
		// se não tem prox acaba a repeticao

		while (resultado.next()) {
			Produtos p = new Produtos();
			p.setCodigo(resultado.getLong("codigo"));
			p.setProduto(resultado.getString("produto"));

			lista.add(p);
		}

		return lista;
	}
	
	public ArrayList<Produtos> buscarPorProduto(Produtos p)throws SQLException{
		
		/*StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao");
		*/
		
		String sql = new String();
		
		sql = ("SELECT codigo, produto FROM produtos WHERE produto LIKE ? ORDER BY produto");
	
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, "%"+p.getProduto()+"%");

		ResultSet resultado = comando.executeQuery();

		ArrayList<Produtos> lista = new ArrayList<Produtos>();

		while (resultado.next()) {
			Produtos item = new Produtos();
			item.setCodigo(resultado.getLong("codigo"));
			item.setProduto(resultado.getString("produto"));

			lista.add(item);
		}

		return lista;
		
	}

	// somente para testar os metodos.

	public static void main(String[] args) {

		/*
		 * Fabricante f1 = new Fabricante(); f1.setDescricao("DESCRICAO 1");
		 * Fabricante f2 = new Fabricante(); f2.setDescricao("DESCRICAO 2");
		 * 
		 * FabricanteDAO fdao = new FabricanteDAO();
		 * 
		 * try { fdao.salvar(f1); fdao.salvar(f2); System.out.println(
		 * "Os fabricants foram cadastrados com sucesso!"); } catch(SQLException
		 * e){ System.out.println(
		 * "Ocorreu um erro ao tentar salvar um dos fabricantes ERRO: " + e);
		 * e.printStackTrace(); }
		 */

		// somente para testar a exclusão.

		/*
		 * Fabricante f1 = new Fabricante(); f1.setCodigo(1L); Fabricante f2 =
		 * new Fabricante(); f2.setCodigo(2L);
		 * 
		 * FabricanteDAO fdao = new FabricanteDAO();
		 * 
		 * try { fdao.excluir(f1); fdao.excluir(f2); System.out.println(
		 * "Os fabricantes foram excluidos com sucesso!"); } catch (SQLException
		 * e) { // TODO Auto-generated catch block e.printStackTrace();
		 * System.out.println("Erro ao escluir fabricantes. ERRO " + e); }
		 */

		/*
		 * Fabricante f1 = new Fabricante(); f1.setCodigo(4L); f1.setDescricao(
		 * "DESCRICAO 50");
		 * 
		 * FabricanteDAO fdao = new FabricanteDAO(); try { fdao.editar(f1);
		 * System.out.println("O Fabricante foi editado com sucesso "); } catch
		 * (SQLException e) { // TODO Auto-generated catch block
		 * System.out.println("Ocorreu um erro ao editar Fabricante. ERRO: " +
		 * e); e.printStackTrace(); }
		 */

		/*
		 * Fabricante f1 = new Fabricante(); f1.setCodigo(4L); Fabricante f2 =
		 * new Fabricante(); f2.setCodigo(5L);
		 * 
		 * FabricanteDAO fdao = new FabricanteDAO();
		 * 
		 * try { Fabricante f3 = fdao.buscarPorCodigo(f1); Fabricante f4 =
		 * fdao.buscarPorCodigo(f2);
		 * 
		 * System.out.println("Resultado 1: " + f3); System.out.println(
		 * "Resultado 2: " + f4);
		 * 
		 * } catch (SQLException e) { System.out.println(
		 * "Ocorreu um erro ao pesquisar Fabricante. ERRO: " + e);
		 * e.printStackTrace(); }
		 */

		/*
		 * FabricanteDAO fdao = new FabricanteDAO(); try { ArrayList<Fabricante>
		 * lista = fdao.listar();
		 * 
		 * for(Fabricante f : lista){ System.out.println("Resultado:" + f);
		 * 
		 * } } catch (SQLException e) { System.out.println(
		 * "Ocorreu um erro ao tentar listar os fabricantes");
		 * e.printStackTrace(); }
		 */

		
		/*FabricanteDAO fdao = new FabricanteDAO();
		try {
			ArrayList<Fabricante> lista = fdao.listar();

			for (Fabricante f : lista) {
				System.out.println("Resultado: " + f);
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao tentar listar os fabricantes!");
			e.printStackTrace();

		}*/
		
		Produtos p1 = new Produtos();
		p1.setProduto("50");
		
		ProdutosDAO pdao = new ProdutosDAO();
		try{
		ArrayList<Produtos> lista = pdao.buscarPorProduto(p1);
		
		for (Produtos p : lista) {
			System.out.println("Resultado: " + p);
		}
		}catch(SQLException e){
			
		System.out.println("Erro ao tentar pesquisar um Fabricante");
			e.printStackTrace();
		}
	}
}
