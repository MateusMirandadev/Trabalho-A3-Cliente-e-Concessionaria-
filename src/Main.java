import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GerenciadorCliente clienteEditor =  new GerenciadorCliente();
        Scanner scanner = new Scanner(System.in);
        int opicoes;
        
        do{
            System.out.println("\n**** MENU ****");
            System.out.println("1. Criar um novo registro");
            System.out.println("2. Lista dos registros");
            System.out.println("3. Buscar um registro");
            System.out.println("4. Editar registro");
            System.out.println("5. Excluir registro");
            System.out.println("6. Sair");
            System.out.println("\n******************");
            opicoes = scanner.nextInt();
            scanner.nextLine();

            try{
                switch (opicoes) {
                    case 1: {
                        System.out.println("Digite o nome do cliente: ");
                        String nome = scanner.nextLine();
                        System.out.println("Digite a idade do cliente: ");
                        int idade = scanner.nextInt();
                        System.out.println("Digite o cpf do cliente: ");
                        scanner.nextLine();
                        String cpf = scanner.nextLine();
                        System.out.println("Digite o carro escolhido pelo cliente: ");
                        String carro = scanner.nextLine();
                        System.out.println("Digite a cor escolhida pelo cliente: ");
                        String cor = scanner.nextLine();
                        System.out.println("Cliente adicionado com sucesso!");
                        Cliente novoCliente = new Cliente(clienteEditor.proximoId(), nome, idade , cpf, carro, cor);
                        clienteEditor.adicionar(novoCliente);
                        break;
                    }
                    case 2: {
                        List<Cliente> listaCliente = clienteEditor.listarClientes();
                        if (listaCliente.isEmpty()){
                            System.out.println("Nenhum cliente cadastrado");
                        } else {
                            System.out.println("\n**** Lista de Clientes ****");
                            for (Cliente cliente : listaCliente){
                                System.out.println(cliente);
                            }
                        }
                        break;
                    }
                    case 3: {
                        System.out.println("ID: ");
                        int id = scanner.nextInt();
                        Cliente clienteEncontrado = clienteEditor.buscarId(id);
                        System.out.println(clienteEncontrado != null ? clienteEncontrado : "Cliente não existe ou não foi encontrado!");
                        break;
                    }
                    case 4: {
                        System.out.println("Digite o ID do cliente que deseja editar: ");
                        int id = scanner.nextInt();
                        Cliente edicaoCliente = clienteEditor.buscarId(id);
                        if(edicaoCliente == null){
                            System.out.println("Cliente não existe ou não foi encontrado!");
                            break;
                        }else{
                            try{
                            System.out.println("Novo nome do cliente: ");
                            scanner.nextLine();
                            String novoNome = scanner.nextLine();
                            System.out.println("Nova idade do cliente: ");
                            int novaIdade = scanner.nextInt();
                            System.out.println("Novo CPF do cliente: ");
                            scanner.nextLine();
                            String novoCpf = scanner.nextLine();
                            System.out.println("Novo carro do cliente: ");
                            String novoCarro = scanner.nextLine();
                            scanner.nextLine();
                            System.out.println("Nova cor do carro: ");
                            String novaCor = scanner.nextLine();
                            scanner.nextLine();
                            edicaoCliente.setNome(novoNome);
                            edicaoCliente.setIdade(novaIdade);
                            edicaoCliente.setCPF(novoCpf);
                            edicaoCliente.setModeloCarro(novoCarro);
                            edicaoCliente.setcorCarro(novaCor);
                            clienteEditor.atualizarArquivo(edicaoCliente);
                            System.out.println("Cliente foi atualizado com sucesso!");
                            } catch (ValidacaoException e){
                                System.out.println("Erro ao editar o cliente." + e.getMessage());
                            }
                        }
                        break;
                    }
                    case 5: {
                        System.out.println("Id do cliente a ser excluido: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        clienteEditor.deletarArquivo(id);
                        System.out.println("Cliente excluido com sucesso!");
                        break;
                    }
                    case 6: {
                        System.out.println("Saindo...");
                        break;
                    }
                    default: {
                        System.out.println("Numero invalido.");
                        break;
                }
                } 
            }catch (ValidacaoException e) {
                    System.out.println("Erro na validação:" + e.getMessage());
                }
        } while (opicoes != 6);
    }
}
