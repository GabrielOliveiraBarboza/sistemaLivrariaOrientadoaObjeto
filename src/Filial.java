import java.util.List;
import java.util.ArrayList;


public class Filial {
    int codigo;
    String nome;
    String endereco;
    String contato;
    List<Livros> livrosEmEstoque;

    public int getCodigo() {

        return codigo;
    }

    public String getNome() {

        return nome;
    }

    public String getEndereco() {

        return endereco;
    }

    public String getContato() {

        return contato;
    }
    public List<Livros> getLivrosEmEstoque() {

        return livrosEmEstoque;
    }
    public Filial(int codigo, String nome, String endereco, String contato) {
        this.codigo = codigo;
        this.nome = nome;
        this.endereco = endereco;
        this.contato = contato;
        this.livrosEmEstoque = new ArrayList<>();
    }
}
