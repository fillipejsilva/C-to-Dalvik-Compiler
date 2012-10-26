package org.tree;

import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;

public class SymTable {
	public HashMap <String, StFuncao> funcoes;

	public SymTable() {
		funcoes = new HashMap <String, StFuncao>();
	}

	public boolean compareTypes (Tipo t1, Tipo t2) {
		if(t1 == null || t2 == null) return false;

		if(t1 instanceof IntTipo && t2 instanceof IntTipo) return true;

		//if(t1 instanceof VoidTipo && t2 instanceof VoidTipo) return true; Operacoes com void nao sao permitidas

		if(t1 instanceof FloatTipo && t2 instanceof FloatTipo) return true;

		if(t1 instanceof CharTipo && t2 instanceof CharTipo) return true;

		return false;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();

		for (String key: this.funcoes.keySet()) {
		  buffer.append("\n");
			buffer.append(this.funcoes.get(key).tipo.getClass());
			buffer.append(" : " + key);
			
			buffer.append("\n\tParams:");
			for(String key2 : this.funcoes.get(key).params.keySet()) {
				buffer.append("\n\t\t" + this.funcoes.get(key).params.get(key2).tpo.getClass());
				buffer.append(" : " + key2);
			}

			buffer.append("\n\tLocais:");
			for(String key3 : this.funcoes.get(key).locais.keySet()) {
				buffer.append("\n\t\t" + this.funcoes.get(key).locais.get(key3).tpo.getClass());
				buffer.append(" : " + key3);
				buffer.append("[" + this.funcoes.get(key).locais.get(key3).tam + "]");
			}

			buffer.append("\n");
		}

		return buffer.toString();
	}
}
