package sample3;

/* Aus dem PP WS 99 WS 99 */

attr "int" boxWidth, boxHeight, boxDepth with Box, ItemList;
attr "int" actual with Item;
attr "boolean" inHBox with ItemList;
attr "int" requSize with ItemList;


Page		::= Box:content
Box			::= {HBox}
			    "int"    :requestedWidth
			    ItemList :content
		  	  | {VBox}
			    "int"    :requestedHeight HAlignment:halign
			    ItemList :content
		  	  | {EmptyBox}
			    "int" :width  "int" :height  "int" :depth
			  | {Rule}
			     "int":width  "int" :height  "int" :depth
		  	  | {TextBox}
			    String:text   "java.awt.Font" :font

ItemList	::= Item*

Item		::= {Glue}
			    "int" :natural  "int" :plus  "int" :minus
		  	  | {BoxItem} 
			    Box   :box

HAlignment	::= {Left} | {HCenter} | {Right} 


