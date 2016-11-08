package br.com.matriz.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.matriz.dao.MatrizDAO;
import br.com.matriz.dao.ProdutosDAO;
import br.com.matriz.domain.Matriz;
import br.com.matriz.domain.Produtos;
import br.com.matriz.util.JSFUtil;

@ManagedBean(name = "MBMatriz")
@ViewScoped
public class MatrizBean {

	// guarda a consulta geral
	private ArrayList<Matriz> itens;
	// guarda a consulta filtrada
	private ArrayList<Matriz> itensFiltrados;

	// guardar dados da inclusão
	private Matriz produto;
	// guarda dados do combo dos fabricantes
	private ArrayList<Produtos> comboProdutos;

	public ArrayList<Matriz> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Matriz> itens) {
		this.itens = itens;
	}

	public ArrayList<Matriz> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Matriz> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public Matriz getProduto() {
		return produto;
	}

	public void setProduto(Matriz produto) {
		this.produto = produto;
	}

	public ArrayList<Produtos> getComboProdutos() {
		return comboProdutos;
	}

	public void setComboProdutos(ArrayList<Produtos> comboProdutos) {
		this.comboProdutos = comboProdutos;
	}

	public void carregarListagem() {
		try {
			MatrizDAO dao = new MatrizDAO();
			itens = dao.listar();

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void prepararNovo() {

		try {
			produto = new Matriz();
			
			ProdutosDAO dao = new ProdutosDAO();
			comboProdutos = dao.listar();

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}

	public void novo() {

		try {

			MatrizDAO dao = new MatrizDAO();
			dao.salvar(produto);

			itens = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Matriz salva com sucesso.");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}

	public void excluir() {

		try {
			MatrizDAO dao = new MatrizDAO();
			dao.excluir(produto);

			itens = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Matriz removido com sucesso");

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}

	public void prepararEditar() {
		try {
			ProdutosDAO dao = new ProdutosDAO();
			comboProdutos = dao.listar();

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void editar() {
		try {
			MatrizDAO dao = new MatrizDAO();
			dao.editar(produto);
			itens = dao.listar();
			
			JSFUtil.adicionarMensagemSucesso("Produto editado com sucesso");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}
