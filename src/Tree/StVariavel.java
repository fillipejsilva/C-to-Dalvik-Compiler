package org.tree;

public class StVariavel {
	public Tipo tpo;
	public int tam;
	public String id;
	public Object valor;
	
	public StVariavel(){
		tpo = null;
		tam = 0;
		id = null;
		valor = null;
	}
	
	public void setValor(Object val){
		
		if(val instanceof Integer){
			valor = new Integer ( ((Integer) val).intValue() );
		}
		else if(val instanceof Float){
			valor = new Float ( ((Float) val).floatValue() );
		}
		else if(val instanceof String){
			valor = new String ( (String) val );
		}

	}

	public StVariavel(Declara decl) {
		this.tpo = decl.tpo;
		this.tam = decl.vt.vt;
		this.id  = decl.i.s;
	}

	public StVariavel(Entrada ent) {
		this.id = ent.i.s;
		this.tam = 0;
		this.tpo = ent.tpo;
	}
}
