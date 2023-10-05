public class Livros {

    String codigo;
    String titulo;

    String editora;
    String area;
    int ano;
    double valor;
    int qtdEstoque;

// Abaixo criei um metodo construtor , o contrututor server para criar o objeto em memória. Devemos criar pois
// utilizaremos quando formos instansiar a classe.
    public Livros(String codigo, String titulo, String editora, String area, int ano, double valor, int qtdEstoque) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.editora = editora;
        this.area = area;
        this.ano = ano;
        this.valor = valor;
        this.qtdEstoque = qtdEstoque;
    }

  // Criei o método GET para acessar os atributos e retornar.

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEditora() {
        return editora;
    }

    public String getArea() {
        return area;
    }

    public int getAno() {
        return ano;
    }

    public double getValor() {
        return valor;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }


    //metodo feito para clacular o valor de estoque

    public double getValorTotal() {
        return valor*qtdEstoque;
    }
}
