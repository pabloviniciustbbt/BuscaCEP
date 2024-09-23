package com.pabloleal.buscacep;

import com.pabloleal.buscacep.models.Cep;
import com.pabloleal.buscacep.services.Services;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Services apiServices = new Services();
        String cepInput;
        Scanner scan = new Scanner(System.in);
        Cep cep;
        int opcao = 1;

        System.out.println("\n=================================");
        System.out.println("            BUSCA CEP    ");
        System.out.println("=================================");

        while (opcao != 0){
            System.out.println("\nEscolha uma das opções abaixo");
            System.out.println("\n1. Consultar CEP");
            System.out.println("0. Sair");
            System.out.print("\nDigite aqui: ");
            opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao){
                case 1:
                        System.out.print("\nDigite um CEP para consulta: ");
                        cepInput = scan.nextLine();

                        if (cepInput.equalsIgnoreCase("0")){
                            System.out.println("\nEncerrando o programa...");
                            System.exit(0);
                        }

                        if(cepInput.length() != 8 || cepInput.matches(".*[a-zA-Z].*")){
                            System.out.println("\nCep inválido. Por favor, digite um CEP válido. ");
                            continue;
                        }

                        try{
                            String result = apiServices.cepRequest(cepInput);
                            if (result == null){
                                continue;
                            }
                            cep = apiServices.jsonConverter(result);
                            System.out.println(cep);
                            apiServices.jsonWriter(cep);
                        } catch (Exception e){
                            System.out.println(e.getMessage());
                        }

                    break;
                case 0:
                    System.out.println("\nSaindo...");
                    System.exit(0);
                default:
                    System.out.println("\nOpção Inválida");
            }

        }
        scan.close();
    }
}
