package entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pessoa {
	private String nome;
	private LocalDate dataNasc;
	
	public Pessoa(String nome, LocalDate dataNasc) {
		this.nome = nome;
		this.dataNasc = dataNasc;
	}

	public String getNome() {
		return nome;
	}

	public LocalDate getDataNasc() {
		return dataNasc;
	}
	
	public String formatarData() {
        return dataNasc.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
	
	@Override
    public String toString() {
        return "Nome: " + nome + ", Data de Nascimento: " + formatarData();
    }

}
