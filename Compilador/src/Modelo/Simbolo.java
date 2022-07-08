package Modelo;


public class Simbolo {
	
	private int codigo;
	private String simbolo;
	private String tipo;
	private int nivel;
	private String categoria;
	private String token;
	
	public Simbolo(int codigo, String simbolo) {
		this.setCodigo(codigo);
		this.setSimbolo(simbolo);
	}
	
	public Simbolo(String token, String categoria, String tipo, int nivel) {
		this.setToken(token);
        this.setCategoria(categoria);
        this.tipo = tipo;
        this.nivel = nivel;
	}
	
	public int getCodigo()
	{
		return codigo;
	}
	
	public void setCodigo(int codigo)
	{
		this.codigo = codigo;
	}
	
	public String getSimbolo()
	{
		return simbolo;
	}
	
	public void setSimbolo(String simbolo)
	{
		this.simbolo = simbolo;
	}
	
	public String getTipo()
	{
		return tipo;
	}
	
	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}
	
	public int getNivel()
	{
		return nivel;
	}
	
	public void setNivel(int nivel)
	{
		this.nivel = nivel;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	

}
