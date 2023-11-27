import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LivrariaService {

    public List<Livros> livros;
    public List<Filial> filialList;

    public LivrariaService() {
        this.livros = new ArrayList<>();
        this.filialList = new ArrayList<>();
    }

    public void cadastrarLivro(Livros livro, Filial filial) {
        livro.setFilial(filial);
        filial.getLivrosEmEstoque().add(livro);
        livros.add(livro);
    }

    public void criarFilial(Filial filial) {

        filialList.add(filial);
    }

    public void listarLivros() {
        for (Livros livro : livros) {
            livro.info();
        }
    }

    public List<Livros> buscarLivrosPorTitulo(String titulo, int codigoFilial) {
        List<Livros> buscaLivro = new ArrayList<>();

        for (Filial filial : filialList) {
            if (filial.getCodigo() == codigoFilial) {
                List<Livros> livrosEmEstoque = filial.getLivrosEmEstoque();
                if (livrosEmEstoque != null) {
                    for (Livros livro : livrosEmEstoque) {
                        if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                            buscaLivro.add(livro);
                        }
                    }
                } else {
                    System.out.println("A lista de livros em estoque é nula para a filial com código " + codigoFilial);
                }

            }
        }

        return buscaLivro;
    }


    public List<Livros> buscarLivroPorCategoria(String categoria, int codigoFilial) {
        List<Livros> resultados = new ArrayList<>();
        for (Livros livro : livros) {
            Filial filialLivro = livro.getFilial();
            if (filialLivro != null && filialLivro.getCodigo() == codigoFilial && livro.getArea().equalsIgnoreCase(categoria)) {
                resultados.add(livro);
            }
        }
        return resultados;
    }


    public List<Livros> buscaPorPreco(double valormi, double valormax, int codigoFilial) {
        List<Livros> livrosPorPreco = new ArrayList<>();
        for (Livros livro : livros) {
            if (livro.getFilial().getCodigo() == codigoFilial && livro.getValor() > valormi && livro.getValor() < valormax) {
                livrosPorPreco.add(livro);
            }
        }
        return livrosPorPreco;
    }

    public List<Livros> buscaProQtEstoque(int valorE, int codigoFilial) {
        List<Livros> QtEstoque = new ArrayList<>();
        for (Livros livro : livros) {
            if (livro.getFilial().getCodigo() == codigoFilial && livro.getQtdEstoque() == valorE) {
                QtEstoque.add(livro);
            }
        }
        return QtEstoque;
    }

    public double calcularEstoque() {
        double valorDeEstoque = 0.0;
        for (Livros livro : livros) {
            valorDeEstoque += livro.getValorTotal();
        }
        return valorDeEstoque;
    }
    public void salvarDadosFiliais() {
        try (PrintWriter escrevendo = new PrintWriter(new FileWriter("src/DadosLivraria.csv"))) {
            for (Filial filial : filialList) {
                escrevendo.println("#FL" + String.format("%02d", filial.getCodigo()) + "," + filial.getNome() + "," +
                        filial.getEndereco() + "," + filial.getContato());

                for (Livros livro : filial.getLivrosEmEstoque()) {
                    escrevendo.println(livro.getCodigo() + "," + livro.getTitulo() + "," + livro.getAno() + "," +
                            livro.getArea() + "," + livro.getEditora() + "," + livro.getValor() + "," +
                            livro.getQtdEstoque());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void carregarDadosFiliais() {
        String caminhoArquivo = "src/DadosLivraria.csv";

        try (FileReader lendo = new FileReader(caminhoArquivo);
             BufferedReader bufferedReader = new BufferedReader(lendo)) {

            String linha;
            while ((linha = bufferedReader.readLine()) != null) {
                if (linha.startsWith("#FL")) {

                    String[] parts = linha.split(",");
                    if (parts.length >= 4) {
                        int codigo = Integer.parseInt(parts[0].substring(3));
                        String nome = parts[1];
                        String endereco = parts[2];
                        String contato = parts[3];

                        Filial filial = new Filial(codigo, nome, endereco, contato);
                        filialList.add(filial);
                    }
                } else {

                    String[] parts = linha.split(",");
                    if (parts.length >= 7) {
                        String codigo = parts[0];
                        String titulo = parts[1];
                        int ano = Integer.parseInt(parts[2]);
                        String area = parts[3];
                        String editora = parts[4];
                        double valor = Double.parseDouble(parts[5].replace("R$", ""));
                        int qtdEstoque = Integer.parseInt(parts[6]);

                        Livros livro = new Livros(codigo, titulo, ano, area, editora, valor, qtdEstoque);
                        cadastrarLivro(livro, filialList.get(filialList.size() - 1));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Filial obterFilialPorCodigo(int codigo) {
        for (Filial filial : filialList) {
            if (filial.getCodigo() == codigo) {
                return filial;
            }
        }
        return null;
    }

    public double calcularEstoqueFilial(Filial filial) {
        double valorDeEstoque = 0.0;
        for (Livros livro : filial.livrosEmEstoque) {
            valorDeEstoque += livro.getValorTotal();
        }
        return valorDeEstoque;
    }
    public void listarEstoqueFilial(int codigoFilial) {
        Filial filial = obterFilialPorCodigo(codigoFilial);

        if (filial != null) {
            double valorEstoqueFilial = calcularEstoqueFilial(filial);
            System.out.println("O valor em estoque na filial " + filial.getNome() + " é: R$ " + valorEstoqueFilial);

            List<Livros> livrosFilial = filial.getLivrosEmEstoque();
            System.out.println("Livros em estoque na filial " + filial.getNome() + ":");
            for (Livros livro : livrosFilial) {
                livro.info();
            }
        } else {
            System.out.println("Filial não encontrada.");
        }
    }
    public boolean verificarExistenciaFilial(int codigoFilial) {
        for (Filial filial : filialList) {
            if (filial.getCodigo() == codigoFilial) {
                return true;
            }
        }
        return false;
    }

    public List<Livros> buscaPorCodigo(String codigo) {
        List<Livros> buscaPorcodigoDolivro = new ArrayList<>();

        for (Livros busca : livros) {
            if (busca.getCodigo().equalsIgnoreCase(codigo)) {
                buscaPorcodigoDolivro.add(busca);
            }
        }
        return buscaPorcodigoDolivro;
    }
}
