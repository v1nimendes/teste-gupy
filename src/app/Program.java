package app;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import entities.Funcionario;

public class Program {

	public static void main(String[] args) {
		
		//Inserindo os funcionários
		List<Funcionario> func = new ArrayList<>();
		func.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
		func.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
		func.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
		func.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
		func.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
		func.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
		func.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
		func.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
		func.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
		func.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2779.93"), "Gerente"));
		
		//Remover o funcionário “João” da lista
		func.removeIf(f -> f.getNome().equals("João"));
		
		//Imprime todos os funcionários.
		System.out.println("Funcionários:");
        for (Funcionario f : func) {
            System.out.println(f);
        }
        
        //Reajuste de 10%.
        func.forEach(f -> f.setSalario(f.getSalario().multiply(new BigDecimal("1.1"))));
        System.out.println("\nReajuste 10%:");
        for (Funcionario f : func) {
            System.out.println(f);
        }
        
        //Agrupar por função.
        Map<String, List<Funcionario>> funcPorFuncao = func.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));
        
        //Imprime os funcionários por função.
        System.out.println("\nFunções:");
        funcPorFuncao.forEach((funcao, lista) -> {System.out.println("Função: " + funcao);
        lista.forEach(f-> System.out.println(f.getNome()));
        });
        
        //Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        System.out.println("\nFuncionários que fazem aniversário no mês 10 e 12.");
        func.stream().filter(f -> f.getDataNasc().getMonthValue() == 10 || f.getDataNasc().getMonthValue() == 12).forEach(f -> System.out.println(f.getNome()));
        
        //Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
        Funcionario maisVelho = Collections.max(func, Comparator.comparing(f -> ChronoUnit.YEARS.between(f.getDataNasc(), LocalDate.now())));
        System.out.println("\nFuncionário mais velho: " + maisVelho.getNome() + " com idade de " +
        ChronoUnit.YEARS.between(maisVelho.getDataNasc(), LocalDate.now()) + " anos");

        
        //Imprimir a lista de funcionários por ordem alfabética.
        System.out.println("\nFuncionários em ordem alfabética:");
        func.stream().sorted(Comparator.comparing(Funcionario::getNome)).forEach(f -> System.out.println(f.getNome()));
        
        //Imprimir o total dos salários dos funcionários.
        BigDecimal totalSalarios = func.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos salários: " + String.format("%,.2f", totalSalarios));
        
        //Quantos salários mínimos ganha cada funcionário
        System.out.println("\nQuantidade de salários mínimo que cada funcionário ganha: ");
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        func.forEach(funcionario -> {BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
        System.out.printf("%s ganha %.2f salários mínimos%n", funcionario.getNome(), salariosMinimos);
        });
		
	}

}
