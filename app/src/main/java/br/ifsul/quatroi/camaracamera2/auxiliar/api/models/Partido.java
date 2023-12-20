package br.ifsul.quatroi.camaracamera2.auxiliar.api.models;

import com.google.gson.annotations.Expose;

public class Partido {

    private Integer id;

    private String sigla;

    private String nome;

    public Partido() {
    }

    public Partido(Integer id, String sigla, String nome) {
        this.id = id;
        this.sigla = sigla;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        String showSigla = sigla.equalsIgnoreCase(nome) ? "" : " (" + sigla + ")";
        return nome + showSigla;
    }

}
