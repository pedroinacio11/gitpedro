package br.com.matriz.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import br.com.matriz.dao.MatrizDAO;
import br.com.matriz.domain.Matriz;
import br.com.matriz.domain.Produtos;

public class MatrizDAOTeste {
	
	@Test
	public void salvar() throws SQLException{
		
		Matriz m = new Matriz();
		m.setAtividade("Erro ao cadastrar usuario no formulario de cadastro");
		m.setTarefa("36712");
		m.setDtInclusao(new Date());
		m.setDtConclusao(new Date());
		m.setDtPrazo(new Date());
		m.setCriticidade("ALTO");
		m.setTipo("MELHORIA");
		
		Produtos p = new Produtos();
		p.setCodigo(1L);
		
		// uma matriz tem um produto.
		
		m.setProdutos(p);
		
		MatrizDAO dao = new MatrizDAO();
		dao.salvar(m);
	}
	
	@Test
	public void listar() throws SQLException{
		MatrizDAO dao = new MatrizDAO();
		ArrayList<Matriz> lista = dao.listar();
		
		for(Matriz m : lista){
			
			System.out.println("Código de Matriz: " + m.getCodigo());
			System.out.println("Atividade: " + m.getAtividade());
			System.out.println("Tarefa: " + m.getTarefa());
			System.out.println("Data de Inclusão: " + m.getDtInclusao());
			System.out.println("Data de Conclusão: " + m.getDtConclusao());
			System.out.println("Prazo: " + m.getDtPrazo());
			System.out.println("Criticidade: " + m.getCriticidade());
			System.out.println("Tipo: " + m.getTipo());
			System.out.println("Codigo do Produto: " + m.getProdutos().getCodigo());  
			System.out.println("Nome do Produto: " + m.getProdutos().getProduto());
			System.out.println("");
			
		}
		
	}
	
	
	@Test
	public void excluir() throws SQLException{
		
		Matriz m = new Matriz();
		m.setCodigo(2L);
		
		MatrizDAO dao = new MatrizDAO();
		dao.excluir(m);
	}
	
	
	@Test
	public void editar() throws SQLException{
		Matriz m = new Matriz();
	
		m.setCodigo(6L);
		m.setAtividade("Erro");
		m.setTarefa("36712");
		m.setDtInclusao(new Date());
		m.setDtConclusao(new Date());
		m.setDtPrazo(new Date());
		m.setCriticidade("ALTO");
		m.setTipo("MELHORIA");
		
		Produtos p = new Produtos();
		p.setCodigo(1L);
		m.setProdutos(p);
		
		MatrizDAO dao = new MatrizDAO();
		dao.editar(m);
		
	} 
}