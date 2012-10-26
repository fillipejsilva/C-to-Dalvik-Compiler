package org.tree;

import java.util.HashMap;
/**
 * Funcao e tabela de simbolos locais e params
 */
public class StFuncao implements Cloneable{
	public Tipo tipo;
	public String  id;

	public HashMap <String, StVariavel> params;
	public HashMap <String, StVariavel> locais;

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public StFuncao() {
		params = new HashMap <String, StVariavel>();
		locais = new HashMap <String, StVariavel>();
	}
	
	public StVariavel getVariavel(String key){
		if (this.params.containsKey(key)){
				return this.params.get(key);
		}
		else{
				return this.locais.get(key);
		}
	}
}
