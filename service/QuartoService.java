package service;

import model.Quarto;
import java.util.ArrayList;
import java.util.List;

public class QuartoService {
    private List<Quarto> quartos;

    public QuartoService() {
        quartos = new ArrayList<>();
    }

    public void adicionarQuarto(Quarto quarto) {
        quartos.add(quarto);
    }

    public List<Quarto> listarQuartosDisponiveis() {
        List<Quarto> disponiveis = new ArrayList<>();
        for (Quarto quarto : quartos) {
            if (quarto.getStatus().equals("disponível")) {
                disponiveis.add(quarto);
            }
        }
        return disponiveis;
    }

    public Quarto buscarQuarto(int numero) {
        for (Quarto quarto : quartos) {
            if (quarto.getNumero() == numero) {
                return quarto;
            }
        }
        return null;
    }

    public void atualizarStatusQuarto(int numero, String status) {
        Quarto quarto = buscarQuarto(numero);
        if (quarto != null) {
            quarto.setStatus(status);
        }
    }
}
