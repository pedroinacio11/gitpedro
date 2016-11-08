package br.com.matriz.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.matriz.dao.ProdutosDAO;
import br.com.matriz.domain.Produtos;
import br.com.matriz.util.JSFUtil;

@ManagedBean(name = "MBProdutos")
@ViewScoped
public class ProdutosBean {
	private Produtos produto;
	private ArrayList<Produtos> itens;
	private ArrayList<Produtos> itensFiltrados;

	public Produtos getProdutos() {
		return produto;
	}

	public void setProdutos(Produtos produto) {
		this.produto = produto;
	}

	public ArrayList<Produtos> getItens() {
		return itens;
	}
	
	public void setItens(ArrayList<Produtos> itens) {
		this.itens = itens;
	}
	
	public ArrayList<Produtos> getItensFiltrados() {
		return itensFiltrados;
	}
	
	public void setItensFiltrados(ArrayList<Produtos> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}
	
	// O metodo abaixo sera chamado antes da pagina ser carregada
	// pra isso serve o PostContruct.
	@PostConstruct
	public void prepararPesquisa() {
		try {
			// pegando o valor do dao e colocando dentro do
			// listDataModel(jogando na tela)
			ProdutosDAO dao = new ProdutosDAO();
			itens = dao.listar();
		
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro("Ocorreu o seguinte erro: " + ex.getMessage());

		}
	}

	public void prepararNovo() {
		produto = new Produtos();

	}

	// Cadastrar um novo Produto.
	public void novo() {
		try {
			ProdutosDAO dao = new ProdutosDAO();
			dao.salvar(produto);
			itens = dao.listar();
			JSFUtil.adicionarMensagemSucesso("Produto cadastrado com sucesso");

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro("Ocorreu o seguinte erro: " + ex.getMessage());
		}
	}
	
	public void excluir() {

		try {
			ProdutosDAO dao = new ProdutosDAO();
			dao.excluir(produto);
			itens = dao.listar();
			JSFUtil.adicionarMensagemSucesso("Produto removido com sucesso.");

		} catch (Exception ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro("Ocorreu o seguinte erro: " + ex.getMessage());
		}
	}

	public void editar() {
		try {
			ProdutosDAO dao = new ProdutosDAO();
			dao.editar(produto);
			itens = dao.listar();
			JSFUtil.adicionarMensagemSucesso("Produto editado com sucesso.");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro("Ocorreu o seguinte erro: " + ex.getMessage());
		}
	}
}
