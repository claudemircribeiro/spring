package br.com.casadocodigo.loja.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Price {
	@Column(scale = 2)
	private BigDecimal preco;
	
	@Column(name="bookType",columnDefinition="varchar(255)")
	@Enumerated(EnumType.STRING)
	private BookType bookType;
	public BigDecimal getPreco() {
		return preco;
	}
	public BookType getBookType() {
		return bookType;
	}
	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	
}
