package br.com.matriz.domain;

import java.util.Date;

public class Matriz {
	
	private Long codigo;
	private Produtos produtos = new Produtos();
	private String atividade;
	private String tarefa;
	private Date dtInclusao;
	private Date dtConclusao;
	private Date dtPrazo;
	private String criticidade;
	private String tipo;
	

	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Produtos getProdutos() {
		return produtos;
	}
	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}
	public String getAtividade() {
		return atividade;
	}
	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}
	public String getTarefa() {
		return tarefa;
	}
	public void setTarefa(String tarefa) {
		this.tarefa = tarefa;
	}
	
	/*
	public Date getDtInclusao() {
		return dtInclusao;
	}
	public void setDtInclusao(int dia, int mes, int ano) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH,dia);
		cal.set(Calendar.MONTH,mes);
		cal.set(Calendar.YEAR,ano);
		
		Date d = cal.getTime();
		this.dtInclusao = d;
	} */
	
	
	
	public Date getDtInclusao() {
		return dtInclusao;
	}
	public void setDtInclusao(Date dtInclusao) {
		this.dtInclusao = dtInclusao;
	}
	

	public Date getDtConclusao() {
		return dtConclusao;
	}
	public void setDtConclusao(Date dtConclusao) {
		this.dtConclusao = dtConclusao;
	}
	public Date getDtPrazo() {
		return dtPrazo;
	}
	public void setDtPrazo(Date dtPrazo) {
		this.dtPrazo = dtPrazo;
	}
	/*
	public Date getDtConclusao() {
		return dtConclusao;
	}
	
	public void setDtConclusao(int dia, int mes, int ano) {
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH,dia);
		cal.set(Calendar.MONTH, mes);
		cal.set(Calendar.YEAR, ano);
		
		Date d = cal.getTime();
		this.dtConclusao = d;
	}
	
	public Date getDtPrazo() {
		return dtPrazo;
	}
	
	public void setDtPrazo(int dia, int mes, int ano) {
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH,dia);
		cal.set(Calendar.MONTH, mes);
		cal.set(Calendar.YEAR, ano);
		
		Date d = cal.getTime();
		this.dtPrazo = d;
	}

*/
	public String getCriticidade() {
		return criticidade;
	}
	public void setCriticidade(String criticidade) {
		this.criticidade = criticidade;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
