package br.edu.cafeteria.app;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import br.edu.cafeteria.modelo.*;
import br.edu.cafeteria.excecao.EstoqueInsuficienteException;
import br.edu.cafeteria.excecao.PontosInsuficientesException;

public class Main {
    public static void main(String[] args) {
        try (Scanner teclado = new Scanner(System.in)) {
            List<Produto> cardapio = new ArrayList<>();
            
            cardapio.add(new Comida(101, "Pão de Lembas (Tradicional)", 15.00, 10, false, false, false, 5));
            cardapio.add(new Comida(102, "Bolo de Caneca Geek", 12.00, 5, false, true, true, 4));
            cardapio.add(new Comida(103, "Cookie de Redstone Vegano", 9.00, 8, true, false, false, 3));
            
            cardapio.add(new Bebida(201, "Café Tradicional (P)", 6.00, 20, "Pequeno", 80.0, 2));
            cardapio.add(new Bebida(202, "Café Tradicional (M)", 8.00, 20, "Médio", 120.0, 2));
            cardapio.add(new Bebida(203, "Café Tradicional (G)", 10.00, 20, "Grande", 160.0, 2));
            
            cardapio.add(new Bebida(204, "Café Expresso (P)", 7.00, 15, "Pequeno", 100.0, 1));
            cardapio.add(new Bebida(205, "Café Expresso (M)", 9.00, 15, "Médio", 140.0, 1));
            cardapio.add(new Bebida(206, "Café Expresso (G)", 11.00, 15, "Grande", 180.0, 2));
            
            cardapio.add(new Bebida(207, "Expresso Duplo (P)", 10.00, 15, "Pequeno", 160.0, 2));
            cardapio.add(new Bebida(208, "Expresso Duplo (M)", 12.00, 15, "Médio", 200.0, 2));
            cardapio.add(new Bebida(209, "Expresso Duplo (G)", 14.00, 15, "Grande", 240.0, 2));
            
            cardapio.add(new Bebida(301, "Suco de Laranja (P)", 7.50, 10, "Pequeno", 0.0, 2));
            cardapio.add(new Bebida(302, "Suco de Laranja (M)", 9.50, 10, "Médio", 0.0, 3));
            cardapio.add(new Bebida(303, "Suco de Laranja (G)", 11.50, 10, "Grande", 0.0, 3));
            
            cardapio.add(new Bebida(304, "Suco de Limão (P)", 6.50, 10, "Pequeno", 0.0, 2));
            cardapio.add(new Bebida(305, "Suco de Limão (M)", 8.50, 10, "Médio", 0.0, 3));
            cardapio.add(new Bebida(306, "Suco de Limão (G)", 10.50, 10, "Grande", 0.0, 3));
            
            cardapio.add(new Bebida(307, "Suco de Acerola (P)", 7.00, 10, "Pequeno", 0.0, 2));
            cardapio.add(new Bebida(308, "Suco de Acerola (M)", 9.00, 10, "Médio", 0.0, 3));
            cardapio.add(new Bebida(309, "Suco de Acerola (G)", 11.00, 10, "Grande", 0.0, 3));
            
            cardapio.add(new Bebida(310, "Suco de Maracujá (P)", 7.50, 10, "Pequeno", 0.0, 2));
            cardapio.add(new Bebida(311, "Suco de Maracujá (M)", 9.50, 10, "Médio", 0.0, 3));
            cardapio.add(new Bebida(312, "Suco de Maracujá (G)", 11.50, 10, "Grande", 0.0, 3));

            List<Cliente> listaClientes = new ArrayList<>();
            listaClientes.add(new ClientePadrao("Emanuele", "123.456.789-00", 0.0));
            listaClientes.add(new ClienteVIP("Victor", "987.654.321-11", 500.0));

            boolean rodarSistema = true;

            while (rodarSistema) {
                System.out.println("\n=== SISTEMA BYTE & BREW INTERATIVO ===");
                System.out.println("1 - Novo Pedido");
                System.out.println("2 - Sair do Sistema");
                System.out.print("Escolha uma opção: ");
                int opcaoMenuPrincipal = teclado.nextInt();
                teclado.nextLine();

                if (opcaoMenuPrincipal == 2) {
                    rodarSistema = false;
                    break;
                } else if (opcaoMenuPrincipal != 1) {
                    System.out.println("Opção inválida!");
                    continue;
                }

                Cliente clienteSelecionado = null;
                boolean identificando = true;

                while (identificando) {
                    System.out.println("\n--- IDENTIFICAÇÃO DO CLIENTE ---");
                    System.out.println("1 - Entrar com CPF");
                    System.out.println("2 - Cadastrar Novo Cliente");
                    System.out.println("3 - Continuar como Anônimo");
                    System.out.print("Escolha uma opção: ");
                    int opcaoIdentificacao = teclado.nextInt();
                    teclado.nextLine();

                    if (opcaoIdentificacao == 1) {
                        System.out.print("Digite o seu CPF: ");
                        String cpfDigitado = teclado.nextLine();
                        
                        for (Cliente c : listaClientes) {
                            if (c.getCpf().equals(cpfDigitado)) {
                                clienteSelecionado = c;
                                break;
                            }
                        }
                        
                        if (clienteSelecionado != null) {
                            System.out.println("Cliente encontrado: " + clienteSelecionado.getNome());
                            identificando = false;
                        } else {
                            System.out.println("CPF não encontrado no sistema!");
                        }

                    } else if (opcaoIdentificacao == 2) {
                        System.out.print("Digite o nome: ");
                        String nomeNovo = teclado.nextLine();
                        System.out.print("Digite o CPF: ");
                        String cpfNovo = teclado.nextLine();
                        System.out.println("Tipo de cliente:\n1 - Padrão\n2 - VIP");
                        System.out.print("Opção: ");
                        int tipoCliente = teclado.nextInt();
                        teclado.nextLine();

                        if (tipoCliente == 2) {
                            clienteSelecionado = new ClienteVIP(nomeNovo, cpfNovo, 0.0);
                            System.out.println("Cliente VIP cadastrado com sucesso!");
                        } else {
                            clienteSelecionado = new ClientePadrao(nomeNovo, cpfNovo, 0.0);
                            System.out.println("Cliente Padrão cadastrado com sucesso!");
                        }
                        
                        listaClientes.add(clienteSelecionado);
                        identificando = false;

                    } else if (opcaoIdentificacao == 3) {
                        clienteSelecionado = new ClientePadrao("Anônimo", "000.000.000-00", 0.0);
                        System.out.println("Continuando como cliente anônimo.");
                        identificando = false;
                    } else {
                        System.out.println("Opção inválida!");
                    }
                }

                Pedido pedido = new Pedido("Atendente Pedro", clienteSelecionado);
                int tempoTotalEspera = 0;

                boolean continuarItens = true;
                while (continuarItens) {
                    System.out.println("\n--- CARDÁPIO BYTE & BREW ---");
                    for (int i = 0; i < cardapio.size(); i++) {
                        Produto p = cardapio.get(i);
                        String infoExtra = "";
                        
                        if (p instanceof Comida) {
                            Comida c = (Comida) p;
                            List<String> tags = new ArrayList<>();
                            if (c.isVegano()) tags.add("Vegano");
                            if (c.isSemGluten()) tags.add("Sem Glúten");
                            if (c.isSemLactose()) tags.add("Sem Lactose");
                            infoExtra = tags.isEmpty() ? "" : " " + tags.toString();
                            infoExtra += " [Preparo: " + c.getTempoPreparoMinutos() + " min]";
                        } else if (p instanceof Bebida) {
                            Bebida b = (Bebida) p;
                            infoExtra = " (Tam: " + b.getTamanho() + ")";
                            if (b.getQuantidadeCafeinaMg() > 0) {
                                infoExtra += " [Cafeína: " + b.getQuantidadeCafeinaMg() + "mg]";
                            }
                            infoExtra += " [Preparo: " + b.getTempoPreparoMinutos() + " min]";
                        }
                        
                        System.out.println((i + 1) + " - " + p.getNome() + infoExtra + " - R$ " + p.getPrecoBase() + " (Estoque: " + p.getQuantidadeEstoque() + ")");
                    }
                    System.out.println((cardapio.size() + 1) + " - Finalizar Escolha de Itens");
                    System.out.print("Escolha o produto pelo número: ");
                    int escolha = teclado.nextInt();

                    if (escolha == cardapio.size() + 1) {
                        continuarItens = false;
                        break;
                    }

                    if (escolha < 1 || escolha > cardapio.size()) {
                        System.out.println("Opção inválida!");
                        continue;
                    }

                    System.out.print("Digite a quantidade: ");
                    int quantidade = teclado.nextInt();

                    Produto produtoSelecionado = cardapio.get(escolha - 1);

                    try {
                        pedido.adicionarItem(produtoSelecionado, quantidade);
                        System.out.println("-> " + quantidade + "x " + produtoSelecionado.getNome() + " adicionado(s).");
                        
                        int tempoPreparoItem = 0;
                        if (produtoSelecionado instanceof Comida) {
                            tempoPreparoItem = ((Comida) produtoSelecionado).getTempoPreparoMinutos();
                        } else if (produtoSelecionado instanceof Bebida) {
                            tempoPreparoItem = ((Bebida) produtoSelecionado).getTempoPreparoMinutos();
                        }
                        
                        if (tempoPreparoItem > tempoTotalEspera) {
                            tempoTotalEspera = tempoPreparoItem;
                        }
                        
                    } catch (EstoqueInsuficienteException e) {
                        System.out.println("[ERRO ESTOQUE]: " + e.getMessage());
                    }
                }

                double totalPedido = pedido.calcularValorTotal();
                System.out.println("\n--- FECHAMENTO DO PEDIDO ---");
                System.out.println("Pedido nº " + pedido.getNumeroSequencial() + " | Total a pagar: R$ " + totalPedido);
                System.out.println("Tempo médio de espera para o seu pedido: " + tempoTotalEspera + " minutos.");

                boolean pagoComXP = false;

                if (clienteSelecionado instanceof ClienteVIP) {
                    ClienteVIP vip = (ClienteVIP) clienteSelecionado;
                    double pontosNecessarios = totalPedido * ClienteVIP.TAXA_CONVERSAO_XP;
                    
                    System.out.println("\n[SISTEMA DE FIDELIDADE VIP]");
                    System.out.println("Seu Saldo Atual: " + vip.getSaldoXP() + " XP");
                    System.out.println("Pontos necessários para pagar esta conta: " + pontosNecessarios + " XP");

                    if (vip.getSaldoXP() >= pontosNecessarios) {
                        System.out.println("Você TEM saldo de XP suficiente para pagar integralmente!");
                        System.out.println("1 - Sim, pagar com XP | 2 - Não, pagar normal (Dinheiro/Cartão)");
                        System.out.print("Opção: ");
                        int opcaoPagamento = teclado.nextInt();

                        if (opcaoPagamento == 1) {
                            try {
                                vip.pagarComXP(totalPedido);
                                pedido.finalizarVenda();
                                System.out.println("\n=== VENDA FINALIZADA ===");
                                System.out.println("Pagamento efetuado com pontos XP! Novo saldo VIP: " + vip.getSaldoXP() + " XP");
                                pagoComXP = true;
                            } catch (PontosInsuficientesException e) {
                                System.out.println("[ERRO CRÍTICO]: " + e.getMessage());
                            }
                        }
                    } else {
                        System.out.println("Você NÃO possui XP suficiente para o resgate total. Avançando para pagamento normal...");
                    }
                }

                if (!pagoComXP) {
                    pedido.aplicarDesconto(pedido);
                    pedido.finalizarVenda();
                    
                    System.out.println("\n=== VENDA FINALIZADA ===");
                    System.out.println("Pedido pago via Dinheiro/Cartão!");

                    if (!clienteSelecionado.getCpf().equals("000.000.000-00")) {
                        double xpGanhado = clienteSelecionado.calcularXPGanhado(totalPedido);
                        clienteSelecionado.setSaldoXP(clienteSelecionado.getSaldoXP() + xpGanhado);
                        
                        System.out.println("Fidelidade: você ganhou +" + xpGanhado + " XP nesta compra!");
                        System.out.println("Saldo atualizado de " + clienteSelecionado.getNome() + ": " + clienteSelecionado.getSaldoXP() + " XP");
                    }
                }
            }
        }
        System.out.println("\n=== Sistema Encerrado ===");
    }
}