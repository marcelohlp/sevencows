package br.com.sevencows.model;

import java.util.Objects;

import br.com.sevencows.util.Criptografia;

public class Usuario {

	private int codigo;
	private String email;
	private String nome;
	private String senha;

	public Usuario() {

	}

	public Usuario(String email, String nome, String senha) {
		this.email = email;
		this.nome = nome;
		this.senha = senha;
	}

	public Usuario(int codigo, String email, String nome, String senha) {
		super();
		this.codigo = codigo;
		this.email = email;
		this.nome = nome;
		this.senha = senha;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public boolean equals(Object objeto) {

		if (this == objeto) {
			return true;
		} else if (objeto == null || this.getClass() != objeto.getClass()) {
			return false;
		} else {
			Usuario usuario = (Usuario) objeto;
			return Objects.equals(codigo, usuario.getCodigo()) && Objects.equals(email, usuario.getEmail());
		}

	}

	public String toString() {
		return "Nome: " + nome + "\n"
				+ "Email: " + email;
 	}
	
	public boolean validaSenha(String senha) {
	
		if (senha.equals("") || senha == null) {
			
			return false;
			
		} else if (Criptografia.criptografar(senha).equals(this.senha)) {
			
			return true;
			
		} else { 
			
			return false;
			
		}
		
	}

	public int getCodigo() {
		return codigo;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
