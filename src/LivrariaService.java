import java.util.ArrayList;
import java.util.List;

//Criei a classe LivrariaService para deixar o código mais limpo, aqui se encontram os métodos



public class LivrariaService {

    //Aqui criei uma lista de livros , onde utilizarei todos os atributos da classe Livros.

    List<Livros> livros;

    public LivrariaService() {

        this.livros = new ArrayList<>();
    }

    public void cadastrarLivro(Livros livro) {
        // passei como parametro a classe Livro
        livros.add(livro);

    }

    public void listarLivros() {
        // Utilizei um for each para listar todos os livros
        for (Livros livro : livros) {
            System.out.println("Codigo: " + livro.getCodigo());
            System.out.println("Titulo: " + livro.getTitulo());
            System.out.println("Editora: " + livro.getEditora());
            System.out.println("Categoria: " + livro.getArea());
            System.out.println("Ano: " + livro.getAno());
            System.out.println("Valor: R$ " + livro.getValor());
            System.out.println("Estoque: " + livro.getQtdEstoque());
            System.out.println("Valor total em estoque: R$ " + livro.getValor());
            System.out.println();
        }
    }

    public List<Livros> buscarLivrosPorTitulo(String titulo) {
        //Como eu quero buscar o livro pelo titulo, eu passei como parametro o atributo titulo
        //criei uma nova lista que será usada para armazenar os objetos de livros que correspondem ao titulo
        List<Livros> buscaLivro = new ArrayList<>();

        for (Livros livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                // se o titulo for igual ao buscado ele adiciona o objeto livros na nova lista buscaLivro
                buscaLivro.add(livro);
            }
        }

        return buscaLivro;
    }

    public List<Livros> buscarLivroPorCategoria(String categoria) {
        List<Livros> resultados = new ArrayList<>();
        for (Livros livro : livros) {
            if (livro.getArea().equalsIgnoreCase(categoria)) {
                resultados.add(livro);
            }
        }
        return resultados;
    }

    public List<Livros> buscaPorPreco(double valormi, double valormax) {
        List<Livros> livrosPorPreco = new ArrayList<>();
        for (Livros l : livros) {
            if (l.getValor() > valormi && l.getValor() < valormax) {
                livrosPorPreco.add(l);
            }
        }
        return livrosPorPreco;
    }

    public List<Livros> buscaProQtEstoque(int valorE) {
        List<Livros> QtEstoque = new ArrayList<>();
        for (Livros l : livros) {
            if (l.getQtdEstoque() == valorE) {
                QtEstoque.add(l);
            }
        }
        return QtEstoque;
    }

    public double calcularEstoque(){
        double valorDeEstoque=0.0;
        for(Livros livro: livros){
            valorDeEstoque+= livro.getValorTotal();
        }
        return valorDeEstoque;
    }

}