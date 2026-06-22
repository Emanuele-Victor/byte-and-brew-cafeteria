package br.edu.cafeteria.app;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import br.edu.cafeteria.modelo.Comida;
import br.edu.cafeteria.modelo.Bebida;
import br.edu.cafeteria.modelo.ClientePadrao;
import br.edu.cafeteria.modelo.ClienteVIP;
import br.edu.cafeteria.modelo.Pedido;
import br.edu.cafeteria.modelo.Produto;
import br.edu.cafeteria.modelo.Cliente;
import br.edu.cafeteria.excecao.EstoqueInsuficienteException;
import br.edu.cafeteria.excecao.PontosInsuficientesException;

public class Main {
    public static void main(String[] args) {
        try (Scanner teclado = new Scanner(System.in)) {
            Comida paoDeLembas = new Comida(101, "Pão de Lembas", 15.00, 5, 10, false);
            Bebida cafeProgramador = new Bebida(201, "Café do Programador", 8.00, 2, "G", 180.0);
            Bebida pocaoMana = new Bebida(202, "Poção de Mana", 12.00, 10, "M", 0.0);
            
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

                boolean continuarItens = true;
                while (continuarItens) {
                    System.out.println("\n--- MENU DE PRODUTOS ---");
                    System.out.println("1 - " + paoDeLembas.getNome() + " (R$ " + paoDeLembas.getPrecoBase() + " | Estoque: " + paoDeLembas.getQuantidadeEstoque() + ")");
                    System.out.println("2 - " + cafeProgramador.getNome() + " (R$ " + cafeProgramador.getPrecoBase() + " | Estoque: " + cafeProgramador.getQuantidadeEstoque() + ")");
                    System.out.println("3 - " + pocaoMana.getNome() + " (R$ " + pocaoMana.getPrecoBase() + " | Estoque: " + pocaoMana.getQuantidadeEstoque() + ")");
                    System.out.println("4 - Finalizar Escolha de Itens");
                    System.out.print("Escolha o produto: ");
                    int opcaoProduto = teclado.nextInt();

                    if (opcaoProduto == 4) {
                        continuarItens = false;
                        break;
                    }

                    System.out.print("Digite a quantidade: ");
                    int quantidade = teclado.nextInt();

                    Produto produtoSelecionado = null;
                    if (opcaoProduto == 1) produtoSelecionado = paoDeLembas;
                    else if (opcaoProduto == 2) produtoSelecionado = cafeProgramador;
                    else if (opcaoProduto == 3) produtoSelecionado = pocaoMana;

                    if (produtoSelecionado != null) {
                        try {
                            pedido.adicionarItem(produtoSelecionado, quantidade);
                            System.out.println("-> " + quantidade + "x " + produtoSelecionado.getNome() + " adicionado(s) ao pedido.");
                        } catch (EstoqueInsuficienteException e) {
                            System.out.println("[ERRO ESTOQUE]: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Opção de produto inválida!");
                    }
                }

                double totalPedido = pedido.calcularValorTotal();
                System.out.println("\n--- FECHAMENTO DO PEDIDO ---");
                System.out.println("Pedido " + pedido.getNumeroSequencial() + " total: R$ " + totalPedido);

                if (clienteSelecionado instanceof ClienteVIP) {
                    ClienteVIP vip = (ClienteVIP) clienteSelecionado;
                    System.out.println("Como você é VIP, deseja pagar com pontos XP?");
                    System.out.println("1 - Sim | 2 - Não (Pagar normal)");
                    System.out.print("Opção: ");
                    int opcaoPagamento = teclado.nextInt();

                    boolean pagoComSucesso = false;

                    if (opcaoPagamento == 1) {
                        try {
                            double pontosNecessarios = totalPedido * ClienteVIP.TAXA_CONVERSAO_XP;
                            System.out.println("Pontos necessários: " + pontosNecessarios + " XP | Seu Saldo: " + vip.getSaldoXP() + " XP");
                            
                            if (vip.getSaldoXP() < pontosNecessarios) {
                                throw new PontosInsuficientesException("XP insuficiente para pagar a conta de R$ " + totalPedido);
                            }
                            
                            vip.pagarComXP(totalPedido);
                            pedido.finalizarVenda();
                            System.out.println("Pagamento com XP realizado! Novo saldo: " + vip.getSaldoXP() + " XP");
                            pagoComSucesso = true;
                        } catch (PontosInsuficientesException e) {
                            System.out.println("[AVISO PAGAMENTO]: " + e.getMessage());
                            System.out.println("Redirecionando para o pagamento normal (Dinheiro/Cartão)...");
                        }
                    }

                    if (!pagoComSucesso) {
                        pedido.aplicarDesconto(pedido);
                        pedido.finalizarVenda();
                        System.out.println("Pedido pago em dinheiro/cartão com desconto aplicado!");
                    }
                } else {
                    pedido.aplicarDesconto(pedido);
                    pedido.finalizarVenda();
                    if (!clienteSelecionado.getNome().equals("Anônimo")) {
                        ClientePadrao cp = (ClientePadrao) clienteSelecionado;
                        System.out.println("Novo saldo de XP do " + cp.getNome() + ": " + cp.getSaldoXP() + " XP");
                    }
                }
            }
        }

        System.out.println("\n=== Sistema Encerrado ===");
    }
}