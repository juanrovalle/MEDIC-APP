package com.jrolab.medic_app.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) {

        List<String> lista = generarListaStringsAleatorios(100000, 3);

        List<String> filtered = lista.parallelStream().filter(x -> x.contains("A")).sorted().distinct()
                .collect(Collectors.toList());

        filtered.forEach(System.out::println);

        // OperationFunctional();
    }

    private static void OperationFunctional() {
        // interfaz funcional
        Operation op1 = (a, b) -> a + b;
        Operation op2 = (a, b) -> a * b;

        int result1 = op1.operation(10, 10);
        int result2 = op2.operation(5, 5);

        System.out.println(result1 + " " + result2);
    }

    void lamdba1() {

        List<String> lista = generarListaStringsAleatorios(100000, 3);

        lista.forEach(x -> System.out.println(x));
        lista.forEach(x -> {
            System.out.println(x += 1);
        });

        Optional<String> language = lista.parallelStream().
        filter(l -> equals("abc")).findFirst();

        System.out.println(language);

    }

    public static List<String> generarListaStringsAleatorios(int cantidad, int longitud) {
        List<String> lista = new ArrayList<>();
        Random random = new Random();
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        for (int i = 0; i < cantidad; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < longitud; j++) {
                int index = random.nextInt(caracteres.length());
                sb.append(caracteres.charAt(index));
            }
            lista.add(sb.toString());
        }

        return lista;
    }

}
