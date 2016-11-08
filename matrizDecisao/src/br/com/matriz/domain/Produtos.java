package br.com.matriz.domain;

public class Produtos {

	private Long codigo;
	private String produto;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getProduto() {
		return produto;
	}
	public void setProduto(String produto) {
		this.produto = produto;
	}
	
	// ensinar o Java a imprimir o objeto fabricante correto.
		// formatando a saida para o teste que criei no ProdutosDAO
		@Override
		public String toString() {
			String saida = codigo + " - " + produto;
			return saida;
		}
	
}


