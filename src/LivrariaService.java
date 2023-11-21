import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LivrariaService {

    List<Livros> livros;
    List<Filial> filialList;

    public LivrariaService() {

        this.livros = new ArrayList<>();
        this.filialList= new ArrayList<>();
    }

    public void cadastrarLivro(Livros livro, Filial filial) {
        livro.setFilial(filial);
        filial.getLivrosEmEstoque().add(livro);
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
            System.out.println("Valor total em estoque: R$ " + livro.getValorTotal());
            System.out.println();
        }
    }

    public List<Livros> buscarLivrosPorTitulo(String titulo, int codigoFilial) {

        List<Livros> buscaLivro = new ArrayList<>();

        for (Livros livro : livros) {
            if (livro.getFilial().getCodigo() == codigoFilial && livro.getTitulo().equalsIgnoreCase(titulo)) {
                buscaLivro.add(livro);
            }
        }

        return buscaLivro;
    }

    public List<Livros> buscarLivroPorCategoria(String categoria, int codigoFilial) {
        List<Livros> resultados = new ArrayList<>();
        for (Livros livro : livros) {
            if (livro.getFilial().getCodigo()==codigoFilial && livro.getArea().equalsIgnoreCase(categoria)) {
                resultados.add(livro);
            }
        }
        return resultados;
    }

    public List<Livros> buscaPorPreco(double valormi, double valormax, int codigoFilial) {
        List<Livros> livrosPorPreco = new ArrayList<>();
        for (Livros livro : livros) {
            if (livro.getFilial().getCodigo()== codigoFilial && livro.getValor() > valormi && livro.getValor() < valormax) {
                livrosPorPreco.add(livro);
            }
        }
        return livrosPorPreco;
    }

    public List<Livros> buscaProQtEstoque(int valorE, int codigoFilial) {
        List<Livros> QtEstoque = new ArrayList<>();
        for (Livros livro : livros) {
            if (livro.getFilial().getCodigo()==codigoFilial && livro.getQtdEstoque() == valorE) {
                QtEstoque.add(livro);
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
    public Filial obterFilialPorCodigo(int codigo) {
        for (Filial filial : filialList) {
            if (filial.getCodigo() == codigo) {
                return filial;
            }
        }
        return null; // Retorna null se a filial não for encontrada
    }
    public void carregarEstoque() {
        String arquivo = "src/BDLivraria.csv";

        try (FileInputStream fis = new FileInputStream(arquivo); Scanner file = new Scanner(fis)) {
            while (file.hasNextLine()) {
                String[] linha = file.nextLine().split(",");

                String codigo = linha[0];
                String titulo = linha[1];
                String editora = linha[2];
                String area = linha[3];
                int ano = Integer.parseInt(linha[4].trim());
                double valor = Double.parseDouble(linha[5].trim());
                int qtdEstoque = Integer.parseInt(linha[6].trim());
                int codigoFilial = Integer.parseInt(linha[7].trim());

                Livros livro = new Livros(codigo, titulo, editora, area, ano, valor, qtdEstoque);

                Filial filial = obterFilialPorCodigo(codigoFilial);
                if (filial != null) {
                    livro.setFilial(filial);
                    filial.getLivrosEmEstoque().add(livro);
                    livros.add(livro);
                } else {
                    System.out.println("Filial com código " + codigoFilial + " não encontrada. Livro não cadastrado.");
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar o estoque: " + e.getMessage());
        }
    }
    public void salvarEstoque() {
        String arquivo = "src/BDLivraria.csv";

        try (PrintWriter writer = new PrintWriter(arquivo)) {
            for (Livros livro : livros) {
                writer.println(
                        livro.getCodigo() + "," +
                                livro.getTitulo() + "," +
                                livro.getEditora() + "," +
                                livro.getArea() + "," +
                                livro.getAno() + "," +
                                livro.getValor() + "," +
                                livro.getQtdEstoque() + "," +
                                livro.getFilial().getCodigo()
                );
            }
            System.out.println("Estoque salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o estoque: " + e.getMessage());
        }
    }

    public void criarFilial(Filial filial){

        filialList.add(filial);
    }

    public void carregarFilial() {
        String arquivoF = "src/Filial.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoF))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");
                String codigoStr = dados[0].trim().replaceAll("[^0-9]", "");
                if (!codigoStr.isEmpty()) {
                    int codigo = Integer.parseInt(codigoStr);
                    String nome = dados[1];
                    String endereco = dados[2];
                    String contato = dados[3];
                    Filial filial = new Filial(codigo, nome, endereco, contato);
                    criarFilial(filial);
                } else {
                    System.out.println("Código da filial não encontrado ou inválido.");
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar a filial: " + e.getMessage());
        }
    }


    public void salvarFilial() {
        String arquivoF = "src/Filial.txt";
        try (PrintWriter writer = new PrintWriter(arquivoF)) {
            for (Filial filial : filialList) {
                writer.println(
                        "#FL" + filial.codigo + "," +
                                filial.nome + "," +
                                filial.endereco + "," +
                                filial.contato + ","
                );
            }
            System.out.println("Filial salva com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar filial: " + e.getMessage());
        }
    }

    public void listarEstoqueFilial(int codigoFilial) {
        Filial filial = obterFilialPorCodigo(codigoFilial);

        if (filial != null) {
            System.out.println("O valor em estoque na filial " + filial.getNome() + " é : R$ " + calcularEstoqueFilial(filial));
            List<Livros> livrosFilial = filial.getLivrosEmEstoque();
            System.out.println();
            System.out.println("Livros em estoque na filial " + filial.getNome());
            System.out.println();
            for (Livros livro : livrosFilial) {
                livro.info();
            }
        } else {
            System.out.println("Filial não encontrada.");
        }
    }


    public double calcularEstoqueFilial(Filial filial) {
        double valorDeEstoque = 0.0;
        for (Livros livro : filial.livrosEmEstoque) {
            valorDeEstoque += livro.getValorTotal();
        }
        return valorDeEstoque;
    }

    public boolean verificarExistenciaFilial(int codigoFilial) {
        for (Filial filial : filialList) {
            if (filial.codigo == codigoFilial) {
                return true;
            }
        }
        return false;
    }

    public List<Livros> buscaPorCodigo(String codigo){
        List<Livros> buscaPorcodigoDolivro= new ArrayList<>();

        for(Livros busca: livros){
            if(busca.getCodigo().equalsIgnoreCase(codigo)){
                buscaPorcodigoDolivro.add(busca);
            }
        }
        return buscaPorcodigoDolivro;
    }

}