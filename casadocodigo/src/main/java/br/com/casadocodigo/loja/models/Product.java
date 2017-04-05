package br.com.casadocodigo.loja.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotBlank;

@Entity

public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProduto;
	@NotBlank(message="Digita a porra do titulo caraio")
	private String title;
	@Lob
	private String description;
	private Integer pages;
	@ElementCollection
	@CollectionTable(name="produto_preco",joinColumns=@JoinColumn(name="idPreco",referencedColumnName="idProduto"))
	@Enumerated(EnumType.STRING)
	private List<Price> prices = new ArrayList<Price>();
	@OneToOne(cascade = CascadeType.ALL)
	private Capa capa;

	public Capa getCapa() {
		return capa;
	}

	public void setCapa(Capa capa) {
		this.capa = capa;
	}


	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public List<Price> getPrices() {
		return prices;
	}

	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
		
	}
}