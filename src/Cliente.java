public class Cliente {
    private String nomeCliente;
    private int idadeCliente;
    private String cpfCliente; 
    private int nmrCliente;
    public String modeloCarro;
    public String corCarro;

    public void setNome(String setterNome) throws ValidacaoException {
        this.nomeCliente = setterNome;
        if (setterNome == null || setterNome.isBlank()) {
            throw new ValidacaoException("O cliente deve conter um nome.");
        }
    }

    public void setIdade(int setterIdade) throws ValidacaoException {
        this.idadeCliente = setterIdade;
        if (setterIdade < 18) {
            throw new ValidacaoException("O cliente deve ter no mínimo 18 anos.");
        }
    }

    public void setCPF(String setterCPF) throws ValidacaoException {
        this.cpfCliente = setterCPF;
        if (setterCPF == null || !setterCPF.matches("\\d{11}")) {
            throw new ValidacaoException("CPF deve conter 11 dígitos.");
        }
    }

    public void setModeloCarro(String setterModeloCarro){
        this.modeloCarro = setterModeloCarro;
    }

    public void setcorCarro(String setterCorCarro) {
        this.corCarro = setterCorCarro;
    }

    public String getNome() {
        return nomeCliente;
    }

    public int getIdade() {
        return idadeCliente;
    }

    public String getCPF() {
        return cpfCliente;
    }

    public int getNumero() {
        return nmrCliente;
    }

    public String getModeloCarro() {
        return modeloCarro;
    }

    public String getCorCarro() {
        return corCarro;
    }

 public Cliente(int nmr, String nome, int idade, String cpf, String modelo, String cor) throws ValidacaoException {
        setNome(nome);
        setIdade(idade);
        setCPF(cpf);
        this.nmrCliente = nmr;
        this.modeloCarro = modelo;
        this.corCarro = cor;
    }

    public String toCSV() {
        return nmrCliente + "," + nomeCliente + "," + idadeCliente + "," + cpfCliente + "," + modeloCarro + "," + corCarro;
    }

    public static Cliente fromCSV(String linha) throws ValidacaoException {
        String[] partes = linha.split(",");
        if (partes.length != 6) {
            throw new ValidacaoException("Linha inválida no arquivo CSV.");
        }
        int nmr = Integer.parseInt(partes[0]);
        String nome = partes[1];
        int idade = Integer.parseInt(partes[2]);
        String cpf = partes[3];
        String modelo = partes[4];
        String cor = partes[5];

        return new Cliente(nmr, nome, idade, cpf, modelo, cor);
    }

  @Override
public String toString() {
    return "Cliente #" + nmrCliente +
           " | Nome: " + nomeCliente +
           " | Idade: " + idadeCliente +
           " | CPF: " + cpfCliente +
           " | Carro: " + modeloCarro +
           " | Cor: " + corCarro;
}
}
