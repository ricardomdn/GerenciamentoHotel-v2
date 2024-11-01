package model;

import java.util.ArrayList;
import java.util.List;

public class Hospede {
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String endereco;
    private String contato;
    private List<String> historicoEstadias;


    public Hospede(String nome, String cpf, String dataNascimento, String endereco, String contato) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.contato = contato;
        this.historicoEstadias = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public String getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getContato() {
        return contato;
    }
    public void setContato(String contato) {
        this.contato = contato;
    }
    public void adicionarEstadia(String estadia) {
        this.historicoEstadias.add(estadia);
    }
}
