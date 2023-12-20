package br.ifsul.quatroi.camaracamera2.auxiliar.api.models;

public class Deputado {

    private Integer id;

    private UltimoStatus ultimoStatus;

    private String nome;

    public Deputado() {
    }

    public Deputado(Integer id, UltimoStatus ultimoStatus, String nome) {
        this.id = id;
        this.ultimoStatus = ultimoStatus;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UltimoStatus getUltimoStatus() {
        return ultimoStatus;
    }

    public void setUltimoStatus(UltimoStatus ultimoStatus) {
        this.ultimoStatus = ultimoStatus;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }

    public void set(Deputado deputado) {
        id = deputado.id;
        ultimoStatus = deputado.ultimoStatus;
    }

}
