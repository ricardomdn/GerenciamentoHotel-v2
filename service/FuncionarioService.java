package service;

import model.Funcionario;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioService {
    private List<Funcionario> funcionarios;

    public FuncionarioService() {
        funcionarios = new ArrayList<>();
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }

    public Funcionario buscarFuncionario(String cpf) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getCpf().equals(cpf)) {
                return funcionario;
            }
        }
        return null;
    }

    public void editarFuncionario(String cpf, Funcionario novoFuncionario) {
        Funcionario funcionario = buscarFuncionario(cpf);
        if (funcionario != null) {
            funcionario.setNome(novoFuncionario.getNome());
            funcionario.setCargo(novoFuncionario.getCargo());
            funcionario.setSalario(novoFuncionario.getSalario());
            funcionario.setTurno(novoFuncionario.getTurno());
        } else {
            System.out.println("Funcionário com CPF " + cpf + " não encontrado.");
        }
    }

    public List<Funcionario> listarFuncionarios() {
        return funcionarios;
    }
}
