import java.util.ArrayList;
import java.util.List;

public class LivrariaService {
    List<Livros> livros;

    public LivrariaService() {
        this.livros = new ArrayList<>();
    }

    public void cadastrarLivro(Livros livro) {
        //  Scanner scanner= new Scanner(System.in);

        livros.add(livro);

    }

    public void listarLivros() {
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
        List<Livros> buscaLivro = new ArrayList<>();

        for (Livros livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                buscaLivro.add(livro);
            }
        }

        return buscaLivro;
    }

    public List<Livros> buscarLivroPorCategoria(String categoria) {
        List<Livros> resultados = new ArrayList<>();
        for (Livros livro : livros) {
            if (livro.getArea().toLowerCase().equals(categoria.toLowerCase())) {
                resultados.add(livro);
            }
        }
        return resultados;
    }
    public List<Livros> buscaPorPreco (double valor){
        List<Livros> livrosPorPreco= new ArrayList<>();
        for(Livros l: livros){
            if(l.getValor()==valor){
                livrosPorPreco.add(l);
            }
        }
        return livrosPorPreco;
    }
}
