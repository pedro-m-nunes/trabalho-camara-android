package br.ifsul.quatroi.camaracamera2.auxiliar.api.models;

public class UltimoStatus {

    private String nome;

    private String siglaPartido;

    private String siglaUf;

    private String urlFoto;

    private String email;

    public UltimoStatus() {
    }

    public UltimoStatus(String nome, String siglaPartido, String siglaUf, String urlFoto, String email) {
        this.nome = nome;
        this.siglaPartido = siglaPartido;
        this.siglaUf = siglaUf;
        this.urlFoto = urlFoto;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSiglaPartido() {
        return siglaPartido;
    }

    public void setSiglaPartido(String siglaPartido) {
        this.siglaPartido = siglaPartido;
    }

    public String getSiglaUf() {
        return siglaUf;
    }

    public void setSiglaUf(String siglaUf) {
        this.siglaUf = siglaUf;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
