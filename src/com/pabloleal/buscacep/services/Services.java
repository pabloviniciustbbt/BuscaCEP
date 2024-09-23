package com.pabloleal.buscacep.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pabloleal.buscacep.exceptions.ApiException;
import com.pabloleal.buscacep.models.Cep;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Services {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    //Realiza requisição HTTP ao site ViaCep e retorna as informações no formato Json
    public String cepRequest (String busca){
        String responseBody;

        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://viacep.com.br/ws/" + busca + "/json/")).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            responseBody = response.body();

            //Verifica se o CEP existe
            if (response.body().contains("\"erro\": \"true\"")){
                throw new ApiException("\nStatus Code: 404 - O CEP consultado não foi encontrado");
            }

        } catch (ApiException e){
            System.out.println(e.getMessage());
            return null;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return responseBody;
    }

    //Converte a resposta Json em um Objeto Cep usando o Gson
    public Cep jsonConverter(String responseBody) {
        return gson.fromJson(responseBody, Cep.class);
    }

    //Escreve os dados do objeto Cep em um arquivo Json
    public void jsonWriter(Cep cep){
        try {
            FileWriter writer = new FileWriter(cep.getCep() + ".json");
            writer.write(gson.toJson(cep));
            writer.close();
            System.out.println("\n==============================================");
            System.out.println("          Arquivo gerado com Sucesso!");
            System.out.println("==============================================");
        } catch (IOException e) {
            throw new RuntimeException("\nErro ao escrever o arquivo JSON: " + e.getMessage());
        }
    }
}
