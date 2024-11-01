package service;

import model.Hospede;
import java.util.ArrayList;
import java.util.List;

public class HospedeService {
    private List<Hospede> hospedes;

    public HospedeService() {
        hospedes = new ArrayList<>();
    }

    public void adicionarHospede(Hospede hospede) {
        hospedes.add(hospede);
    }

    public Hospede buscarHospede(String cpf) {
        for (Hospede hospede : hospedes) {
            if (hospede.getCpf().equals(cpf)) {
                return hospede;
            }
        }
        return null;
    }

    public void editarHospede(String cpf, Hospede novoHospede) {
        Hospede hospede = buscarHospede(cpf);
        if (hospede != null) {
            hospede.setNome(novoHospede.getNome());
            hospede.setDataNascimento(novoHospede.getDataNascimento());
            hospede.setEndereco(novoHospede.getEndereco());
            hospede.setContato(novoHospede.getContato());

        }
    }

    public List<Hospede> listarHospedes() {
        return hospedes; }
}
