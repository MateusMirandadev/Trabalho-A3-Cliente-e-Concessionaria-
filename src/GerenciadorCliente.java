import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

public class GerenciadorCliente {
    private ArrayList<Cliente> clientes = new ArrayList<>();
    private static final String CLIENTES = "cliente.txt";

    private void carregarArquivo(){
            File clienteArquivo = new File("cliente.txt");
            if(!clienteArquivo.exists()){
                return;
            }
            try (BufferedReader reader = new BufferedReader(new FileReader("cliente.txt"))){
                String linha;
                while ((linha = reader.readLine()) != null) {
                    try{
                        clientes.add(Cliente.fromCSV(linha));
                    } catch (ValidacaoException e){
                        System.out.println("Erro no carregamento dos clientes" + e.getMessage());
                    }
                }
            } catch (IOException e){
                System.out.println("Erro ao ler os clientes" + e.getMessage());
            }
        }
   
    private void salvarArquivo(){
        try (PrintWriter writer = new PrintWriter(new FileWriter("cliente.txt"))){
            for(Cliente c : clientes){
                writer.println(c.toCSV());
            }
        } catch (IOException e){
            System.out.println("Erro ao salvar o arquivo." + e.getMessage());
        }
    }
    
    public void atualizarArquivo(Cliente atualizado){
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getNumero() == atualizado.getNumero()) {
                clientes.set(i, atualizado);
                break;
            }
        }
        salvarArquivo();
    }
    
    public void deletarArquivo(int id) {
        clientes.removeIf(c -> c.getNumero() == id);
        salvarArquivo();
    }   
    
    public void adicionar(Cliente cliente) {
        clientes.add(cliente);
        salvarArquivo();
    }
    
    public int proximoId(){
        return clientes.stream()
                 .mapToInt(Cliente::getNumero)
                 .max()
                 .orElse(0) +1;
    }

    public Cliente buscarId(int id) {
        return clientes.stream()
               .filter(c -> c.getNumero() == id)
               .findFirst()
               .orElse(null);
    }
    
    public ArrayList<Cliente> listarClientes() {
        return clientes;
    }
    public GerenciadorCliente() {
        carregarArquivo();
    }
}


