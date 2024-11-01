package model;

public class Funcionario {
    private String nome;
    private String cpf;
    private String cargo;
    private double salario;
    private String turno;
    private int horasTrabalhadas;
    private double taxaHoraria;

    public Funcionario(String nome, String cpf, String cargo, String turno) {
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = cargo;
        this.turno = turno;
        this.horasTrabalhadas = 0;

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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public void setTaxaHoraria(double taxaHoraria) {
        this.taxaHoraria = taxaHoraria;
    }

    public double calcularSalario() {
        return this.horasTrabalhadas * this.taxaHoraria;
    }

    public void adicionarHoras(int horas) {
        this.horasTrabalhadas += horas;
    }
}
