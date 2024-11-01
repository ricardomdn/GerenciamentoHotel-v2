package main;

import model.Hospede;
import model.Quarto;
import model.Funcionario;
import service.HospedeService;
import service.QuartoService;
import service.FuncionarioService;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class main {
    private static HospedeService hospedeService = new HospedeService();
    private static QuartoService quartoService = new QuartoService();
    private static FuncionarioService funcionarioService = new FuncionarioService();
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static void main(String[] args) {
        while (true) {
            String menu =
                    """
                    1. Cadastrar Hóspede
                    2. Cadastrar Quarto
                    3. Cadastrar Funcionário
                    4. Gerenciar Hóspedes
                    5. Gerenciar Quartos
                    6. Gerenciar Funcionários
                    7. Check-In
                    8. Check-Out
                    9. Reserva
                    10. Listar Quartos Disponíveis
                    11. Listar Hóspedes
                    12. Listar Funcionários
                    13. Folha de Pagamento
                    14. Sair
                    Escolha uma opção:""";
            String escolha = JOptionPane.showInputDialog(menu);

            switch (escolha) {
                case "1":
                    cadastrarHospede();
                    break;
                case "2":
                    cadastrarQuarto();
                    break;
                case "3":
                    cadastrarFuncionario();
                    break;
                case "4":
                    atualizarHospede();
                    break;
                case "5":
                    atualizarQuarto();
                    break;
                case "6":
                    atualizarFuncionario();
                    break;
                case "7":
                    checkIn();
                    break;
                case "8":
                    checkOut();
                    break;
                case "9":
                    Reserva();
                    break;
                case "10":
                    listarQuartosDisponiveis();
                    break;
                case "11":
                    listarHospedes();
                    break;
                case "12":
                    listarFuncionarios();
                    break;
                case "13":
                    processarFolhaDePagamento();
                case "14":
                    JOptionPane.showMessageDialog(null, "Obrigado por usar nosso programa!");
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida. Tente novamente.");
            }
        }
    }

    private static void cadastrarHospede() {
        String nome = JOptionPane.showInputDialog("Nome do hóspede:");
        String cpf = JOptionPane.showInputDialog("CPF do hóspede:");
        String dataNascimento = JOptionPane.showInputDialog("Data de nascimento (DD/MM/AAAA):");
        String endereco = JOptionPane.showInputDialog("Endereço do hóspede:");
        String contato = JOptionPane.showInputDialog("Número de contato do hóspede:");

        Hospede hospede = new Hospede(nome, cpf, dataNascimento, endereco, contato);
        hospedeService.adicionarHospede(hospede);
        JOptionPane.showMessageDialog(null, "Novo hóspede cadastrado com sucesso!");
    }

    private static void cadastrarQuarto() {
        int numero = Integer.parseInt(JOptionPane.showInputDialog("Número do quarto:"));
        String tipo = JOptionPane.showInputDialog("Tipo de quarto (solteiro, casal, suíte):");
        int capacidade = Integer.parseInt(JOptionPane.showInputDialog("Capacidade do quarto:"));
        double preco = Double.parseDouble(JOptionPane.showInputDialog("Preço do quarto:"));

        Quarto quarto = new Quarto(numero, tipo, capacidade, preco);
        quartoService.adicionarQuarto(quarto);
        JOptionPane.showMessageDialog(null, "Novo quarto cadastrado com sucesso!");
    }

    private static void cadastrarFuncionario() {
        String nome = JOptionPane.showInputDialog("Nome do funcionário:");
        String cpf = JOptionPane.showInputDialog("CPF do funcionário:");
        String cargo = JOptionPane.showInputDialog("Cargo do funcionário:");
        String turno = JOptionPane.showInputDialog("Turno de trabalho do funcionário:");

        Funcionario funcionario = new Funcionario(nome, cpf, cargo, turno);
        funcionarioService.adicionarFuncionario(funcionario);
        JOptionPane.showMessageDialog(null, "Novo funcionário cadastrado com sucesso!");
    }

    private static void atualizarHospede() {
        String cpf = JOptionPane.showInputDialog("Informe o CPF do hóspede que deseja atualizar:");
        Hospede hospede = hospedeService.buscarHospede(cpf);

        if (hospede != null) {
            String nome = JOptionPane.showInputDialog("Novo nome:", hospede.getNome());
            String dataNascimento = JOptionPane.showInputDialog("Nova data de nascimento (DD/MM/AAAA):", hospede.getDataNascimento());
            String endereco = JOptionPane.showInputDialog("Novo endereço:", hospede.getEndereco());
            String contato = JOptionPane.showInputDialog("Novo número de contato:", hospede.getContato());

            Hospede novoHospede = new Hospede(nome, cpf, dataNascimento, endereco, contato);
            hospedeService.editarHospede(cpf, novoHospede);
            JOptionPane.showMessageDialog(null, "Dados do hóspede atualizados com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Hóspede não encontrado.");
        }
    }

    private static void processarFolhaDePagamento() {
        String cpf = JOptionPane.showInputDialog("Informe o CPF do funcionário:");

        Funcionario funcionario = funcionarioService.buscarFuncionario(cpf);
        if (funcionario != null) {
            double taxaHoraria = Double.parseDouble(JOptionPane.showInputDialog("Digite quanto o funcionário ganha por hora:"));
            funcionario.setTaxaHoraria(taxaHoraria);

            int horas = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de horas trabalhadas:"));
            funcionario.adicionarHoras(horas);
            JOptionPane.showMessageDialog(null, "Horas registradas com sucesso!");

            double salario = funcionario.calcularSalario();
            JOptionPane.showMessageDialog(null, "Salário total: R$ " + salario);
        } else {
            JOptionPane.showMessageDialog(null, "Funcionário não encontrado.");
        }
    }



    private static void atualizarQuarto() {
        int numero = Integer.parseInt(JOptionPane.showInputDialog("Informe o número do quarto que deseja atualizar:"));
        Quarto quarto = quartoService.buscarQuarto(numero);

        if (quarto != null) {
            String tipo = JOptionPane.showInputDialog("Novo tipo de quarto (solteiro, casal, suíte):", quarto.getTipo());
            int capacidade = Integer.parseInt(JOptionPane.showInputDialog("Nova capacidade do quarto:", quarto.getCapacidade()));
            double preco = Double.parseDouble(JOptionPane.showInputDialog("Novo preço do quarto:", quarto.getPreco()));
            String status = JOptionPane.showInputDialog("Novo status do quarto (disponível, ocupado, em manutenção):", quarto.getStatus());

            quarto.setTipo(tipo);
            quarto.setCapacidade(capacidade);
            quarto.setPreco(preco);
            quarto.setStatus(status);
            JOptionPane.showMessageDialog(null, "Dados do quarto atualizados com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Quarto não encontrado.");
        }
    }

    private static void atualizarFuncionario() {
        String cpf = JOptionPane.showInputDialog("Informe o CPF do funcionário que deseja atualizar:");
        Funcionario funcionario = funcionarioService.buscarFuncionario(cpf);

        if (funcionario != null) {
            String nome = JOptionPane.showInputDialog("Novo nome:", funcionario.getNome());
            String cargo = JOptionPane.showInputDialog("Novo cargo:", funcionario.getCargo());
            String turno = JOptionPane.showInputDialog("Novo turno de trabalho:", funcionario.getTurno());

            Funcionario novoFuncionario = new Funcionario(nome, cpf, cargo, turno);
            funcionarioService.editarFuncionario(cpf, novoFuncionario);
            JOptionPane.showMessageDialog(null, "Dados do funcionário atualizados com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Funcionário não encontrado.");
        }
    }

    private static void checkIn() {
        String cpf = JOptionPane.showInputDialog("Informe o CPF do hóspede:");
        Hospede hospede = hospedeService.buscarHospede(cpf);

        if (hospede != null) {
            int numeroQuarto = Integer.parseInt(JOptionPane.showInputDialog("Informe o número do quarto para check-in:"));
            Quarto quarto = quartoService.buscarQuarto(numeroQuarto);

            if (quarto != null && quarto.getStatus().equals("disponível")) {
                String dataEntrada = JOptionPane.showInputDialog("Data de entrada (DD/MM/AAAA):");
                String dataSaida = JOptionPane.showInputDialog("Data de saída (DD/MM/AAAA):");

                if (validarData(dataEntrada, dataSaida)) {
                    try {
                        Date dataEntradaDate = dateFormat.parse(dataEntrada);
                        quarto.setDataEntrada(dataEntradaDate);

                        quartoService.atualizarStatusQuarto(numeroQuarto, "ocupado");
                        hospede.adicionarEstadia("Entrada: " + dataEntrada + ", Saída: " + dataSaida + ", Quarto: " + numeroQuarto);
                        JOptionPane.showMessageDialog(null, "Check-in realizado com sucesso!");
                    } catch (ParseException e) {
                        JOptionPane.showMessageDialog(null, "Formato de data inválido.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Data de saída deve ser posterior à data de entrada.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Quarto não disponível ou não encontrado.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Hóspede não encontrado.");
        }
    }


    private static void checkOut() {
        int numeroQuarto = Integer.parseInt(JOptionPane.showInputDialog("Informe o número do quarto para check-out:"));
        Quarto quarto = quartoService.buscarQuarto(numeroQuarto);

        if (quarto != null && quarto.getStatus().equals("ocupado")) {
            String dataSaida = JOptionPane.showInputDialog("Data de saída (DD/MM/AAAA):");
            Date dataSaidaDate;
            try {
                dataSaidaDate = dateFormat.parse(dataSaida);
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(null, "Formato de data inválido.");
                return;
            }

            Date dataEntradaDate = quarto.getDataEntrada();
            if (dataEntradaDate != null) {
                long MILISSEGUNDOS_EM_UM_DIA = 1000 * 60 * 60 * 24;

                long diferencaEmMilissegundos = dataSaidaDate.getTime() - dataEntradaDate.getTime();

                int dias = (int) (diferencaEmMilissegundos / MILISSEGUNDOS_EM_UM_DIA);

                double precoTotal = dias * quarto.getPreco();

                quartoService.atualizarStatusQuarto(numeroQuarto, "disponível");
                JOptionPane.showMessageDialog(null, "Check-out realizado com sucesso! Valor total da estadia: R$ " + precoTotal);
            } else {
                JOptionPane.showMessageDialog(null, "Data de entrada não encontrada. Verifique os registros.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Quarto não ocupado ou não encontrado.");
        }
    }
    private static void Reserva() {
        String cpf = JOptionPane.showInputDialog("Informe o CPF do hóspede:");
        Hospede hospede = hospedeService.buscarHospede(cpf);

        if (hospede != null) {
            int numeroQuarto = Integer.parseInt(JOptionPane.showInputDialog("Informe o número do quarto para Reservar:"));
            Quarto quarto = quartoService.buscarQuarto(numeroQuarto);

            if (quarto != null && quarto.getStatus().equals("disponível")) {
                String dataEntrada = JOptionPane.showInputDialog("Data de entrada (DD/MM/AAAA):");
                String dataSaida = JOptionPane.showInputDialog("Data de saída (DD/MM/AAAA):");

                if (validarData(dataEntrada, dataSaida)) {
                    try {
                        Date dataEntradaDate = dateFormat.parse(dataEntrada);
                        quarto.setDataEntrada(dataEntradaDate);

                        quartoService.atualizarStatusQuarto(numeroQuarto, "ocupado");
                        hospede.adicionarEstadia("Entrada: " + dataEntrada + ", Saída: " + dataSaida + ", Quarto: " + numeroQuarto);
                        JOptionPane.showMessageDialog(null, "Reserva realizada com sucesso!");
                    } catch (ParseException e) {
                        JOptionPane.showMessageDialog(null, "Formato de data inválido.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Data de saída inválida, tente novamente.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Quarto não disponível ou não encontrado.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Hóspede não encontrado.");
        }
    }
    private static void listarQuartosDisponiveis() {
        List<Quarto> quartosDisponiveis = quartoService.listarQuartosDisponiveis();
        if (quartosDisponiveis.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum quarto disponível no momento.");
        } else {
            StringBuilder sb = new StringBuilder("Quartos Disponíveis:\n");
            for (Quarto quarto : quartosDisponiveis) {
                sb.append("Número: ").append(quarto.getNumero())
                        .append(", Tipo: ").append(quarto.getTipo())
                        .append(", Capacidade: ").append(quarto.getCapacidade())
                        .append(", Preço: R$ ").append(quarto.getPreco())
                        .append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }

    private static void listarHospedes() {
        List<Hospede> hospedes = hospedeService.listarHospedes();
        if (hospedes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum hóspede cadastrado.");
        } else {
            StringBuilder sb = new StringBuilder("Hóspedes:\n");
            for (Hospede hospede : hospedes) {
                sb.append("Nome: ").append(hospede.getNome())
                        .append(", CPF: ").append(hospede.getCpf())
                        .append(", Data de Nascimento: ").append(hospede.getDataNascimento())
                        .append(", Endereço: ").append(hospede.getEndereco())
                        .append(", Contato: ").append(hospede.getContato())
                        .append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }

    private static void listarFuncionarios() {
        List<Funcionario> funcionarios = funcionarioService.listarFuncionarios();
        if (funcionarios.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum funcionário cadastrado.");
        } else {
            StringBuilder sb = new StringBuilder("Funcionários:\n");
            for (Funcionario funcionario : funcionarios) {
                sb.append("Nome: ").append(funcionario.getNome())
                        .append(", CPF: ").append(funcionario.getCpf())
                        .append(", Cargo: ").append(funcionario.getCargo())
                        .append(", Salário: R$ ").append(funcionario.getSalario())
                        .append(", Turno: ").append(funcionario.getTurno())
                        .append("\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
        }
    }

    private static boolean validarData(String dataEntrada, String dataSaida) {
        try {
            Date entrada = dateFormat.parse(dataEntrada);
            Date saida = dateFormat.parse(dataSaida);
            return saida.after(entrada);
        } catch (ParseException e) {
            return false;
        }
    }
}
