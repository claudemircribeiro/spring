package br.com.casadocodigo.loja.conf;

import java.beans.PropertyEditorSupport;

import br.com.casadocodigo.loja.models.Capa;

public class CartaTyeEditor extends PropertyEditorSupport {
     @Override
    public void setAsText(String text) {
    	Capa c = new Capa();
    	c.setNome(text);
        setValue(c);
    }
     @Override
     public String getAsText() {
    	 if(this.getValue() != null) {
    		 Capa c = (Capa) this.getValue();
    		 return c.getNome();
    	 }
    	 return null;
    	          
     }
}