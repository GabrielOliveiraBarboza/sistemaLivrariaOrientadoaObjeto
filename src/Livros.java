public class Livros {

    String codigo;
    String titulo;

    String editora;
    String area;
    int ano;
    double valor;
    int qtdEstoque;

    public Livros(String codigo, String titulo, String editora, String area, int ano, double valor, int qtdEstoque) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.editora = editora;
        this.area = area;
        this.ano = ano;
        this.valor = valor;
        this.qtdEstoque = qtdEstoque;
    }

    public Livros() {

    }

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
}
