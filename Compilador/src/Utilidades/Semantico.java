package Utilidades;

import java.util.HashMap;
import java.util.Stack;

import javax.swing.JOptionPane;

import InterfaceGrafica.Grafico;
import Modelo.Simbolo;
import Modelo.Token;

public class Semantico {
	
	
	@SuppressWarnings("unlikely-arg-type")
	public static void validar(Stack<Token> pilhaLex) throws Exception {
		
		HashMap<String, Simbolo> tabelaSimbolos = new HashMap<String, Simbolo>();
        int categoria = 0;
        int nivel = 0;
        boolean ehProcedure = false;
        
       
        for(Token t : pilhaLex)
        {
        	 switch (t.getCodigo()) {
             case 2:
            	 categoria = 1;
                 break;
             case 3:
            	 categoria = 2;
                 break;
             case 4:
            	 categoria = 3;
                 break;
             case 5:
            	 categoria = 4;
                 break;
             case 1:
            	 categoria = 6;
                 break;
        	 }
        	 
        	 if(t.getCodigo() == 25)
        	 {
        		 switch(categoria) {
        		 	case 1:
	        		 	if(tabelaSimbolos.containsKey(new Simbolo(t.getSimbolo(), "LABEL", "", nivel)))
	        		 	{
	        		 	 JOptionPane.showMessageDialog(null, "Simbolo " + t.getSimbolo() + " já foi declarado na linha: " + t.getLinha());
	        		 	 Grafico.AdicionarConsole("Simbolo " + t.getSimbolo() + " já foi declarado na linha: " + t.getLinha());
	        			 throw new Exception("");
	        		 	}
	        		 	tabelaSimbolos.put("LABEL", new Simbolo(t.getSimbolo(), "LABEL", "", nivel));
	        		 	break;
        		 	case 2:
        		 		if(tabelaSimbolos.containsKey(new Simbolo(t.getSimbolo(), "CONST", "", nivel)))
	        		 	{
        		 		 JOptionPane.showMessageDialog(null, "Simbolo " + t.getSimbolo() + " já foi declarado na linha: " + t.getLinha());
        		 		 Grafico.AdicionarConsole("Simbolo " + t.getSimbolo() + " já foi declarado na linha: " + t.getLinha());
        		 		 throw new Exception("");
	        		 	}
	        		 	tabelaSimbolos.put("LABEL", new Simbolo(t.getSimbolo(), "CONST", "", nivel));
	        		 	break;
        		 	case 3:
        		 		if(tabelaSimbolos.containsKey(new Simbolo(t.getSimbolo(), "VAR", "INTEIRO", nivel)))
        		 		{
        		 		 JOptionPane.showMessageDialog(null, "Simbolo " + t.getSimbolo() + " já foi declarado na linha: " + t.getLinha());
        		 	     Grafico.AdicionarConsole("Simbolo " + t.getSimbolo() + " já foi declarado na linha: " + t.getLinha());
	        			 throw new Exception("");
	        		 	}
	        		 	tabelaSimbolos.put("LABEL", new Simbolo(t.getSimbolo(), "VAR", "INTEIRO", nivel));
	        		 	break;
        		 	case 4: 
        		 		if(tabelaSimbolos.containsKey(new Simbolo(t.getSimbolo(), "PROCUDERE", "", nivel)))
        		 		{
        		 	     JOptionPane.showMessageDialog(null, "Simbolo " + t.getSimbolo() + " já foi declarado na linha: " + t.getLinha());
        		 		 Grafico.AdicionarConsole("Simbolo " + t.getSimbolo() + " já foi declarado na linha: " + t.getLinha());	
        		 		throw new Exception("");
        		 		}
        		 		ehProcedure = true;
        		 		tabelaSimbolos.put("PROCEDURE", new Simbolo(t.getSimbolo(), "PROCEDURE", "", nivel));
        		 		categoria = 5;
        		 		nivel = 1;
        		 		break;
        		 	case 5:
        		 		if(tabelaSimbolos.containsKey(new Simbolo(t.getSimbolo(), "PARAMETRO", "INTEIRO", nivel)))
        		 		{
        		 	     JOptionPane.showMessageDialog(null, "Simbolo " + t.getSimbolo() + " já foi declarado na linha: " + t.getLinha());
        		 	     Grafico.AdicionarConsole("Simbolo " + t.getSimbolo() + " já foi declarado na linha: " + t.getLinha());	
        		 	    throw new Exception("");
        		 		}
        		 		tabelaSimbolos.put("PARAMETRO", new Simbolo(t.getSimbolo(), "PARAMETRO", "INTEIRO", nivel));
        		 		break;
        		 	case 6:
        		 		tabelaSimbolos.put("PROGRAMA", new Simbolo(t.getSimbolo(), "PROGRAMA", "", nivel));
        		 		break;
        		 }
        	 }
        	 
        	 if(t.getCodigo() == 6)
        	 {
        		 categoria = 0;
        	 }
        	 
        	 if(t.getCodigo() == 7)
        	 {
        		 categoria = 0;
        	 }
        	         	 
        }
        
        nivel = 0;
        
        for(Token t : pilhaLex)
        {
        	if(categoria == 1)
        	{
        		if(t.getCodigo() == 6)
        		{
        			categoria = 2;
        		}
        	}
        	
        	if(categoria == 2)
        	{
        		if(t.getCodigo() == 25)
        		{
        			if(!tabelaSimbolos.containsKey(new Simbolo(nivel, t.getSimbolo())))
        			{
        				JOptionPane.showMessageDialog(null, "Simbolo " + t.getSimbolo() + " já foi declarado na linha: " + t.getLinha());
        				Grafico.AdicionarConsole("Simbolo " + t.getSimbolo() + " já foi declarado na linha: " + t.getLinha());
        				throw new Exception("");
        			}
        		}
        		
        		if(t.getCodigo() == 7)
        		{
        			categoria = 4;
        			nivel = 0;
        		}
        	}
        	
        	if(categoria == 4)
        	{
        		if (t.getCodigo() == 25)
        		{
        			if(!tabelaSimbolos.containsKey(new Simbolo(nivel, t.getSimbolo())))
        			{
        				JOptionPane.showMessageDialog(null, "Simbolo " + t.getSimbolo() + " já foi declarado na linha: " + t.getLinha());
        				Grafico.AdicionarConsole("Simbolo " + t.getSimbolo() + " já foi declarado na linha: " + t.getLinha());
        				throw new Exception("");
        			}
        		}
        		
        		if(t.getCodigo() == 11)
        		{
        			categoria = 5;
        		}
        		////////
        	}
        	
        	if(categoria == 5)
        	{
        		if(t.getCodigo() == 25)
        		{
        			if(!tabelaSimbolos.containsKey(new Simbolo(nivel, t.getSimbolo())))
        			{
        				Grafico.AdicionarConsole("Simbolo " + t.getSimbolo() + " já foi declarado na linha: " + t.getLinha());
        				throw new Exception("Simbolo " + t.getSimbolo() + " já foi declarado na linha: " + t.getLinha());
        			}
        		}
        		
        		if(t.getCodigo() == 36)
        		{
        			nivel = 1;
        		}
        		
        		if(t.getCodigo() == 37)
        		{
        			nivel = 0;
        			categoria = 0;
        		}
        	}
        	
        	if(!ehProcedure)
        	{
        		categoria = 4;
        	}
        }
		
	}

	

}