package com.pabloleal.buscacep.models;

public class Cep {

    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String estado;
    private String uf;
    private String regiao;

    public String getCep() {
        return cep;
    }

    @Override
    public String toString() {
        return "\nCEP: " + cep +
                "\nLogradouro: " + logradouro +
                "\nBairro: " + bairro +
                "\nCidade: " + localidade +
                "\nEstado: " + estado +
                "\nUF: " + uf +
                "\nRegiao: " + regiao;
    }
}
