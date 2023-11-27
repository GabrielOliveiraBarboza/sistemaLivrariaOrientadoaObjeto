public class Livros {

    String codigo;
    String titulo;

    String editora;
    String area;
    int ano;
    double valor;
    int qtdEstoque;
    Filial filial;

    public void setFilial(Filial filial) {

        this.filial = filial;
    }

    public Livros(String codigo, String titulo,int ano, String area, String editora,  double valor, int qtdEstoque) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.ano = ano;
        this.area = area;
        this.editora = editora;
        this.valor = valor;
        this.qtdEstoque = qtdEstoque;
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
    public double getValorTotal() {

        return valor*qtdEstoque;
    }
    public Filial getFilial() {

        return filial;
    }

    public void info() {
        System.out.println(">>>>> Cod#: " + this.getCodigo());
        System.out.println("Titulo: " + this.getTitulo());
        System.out.println("Editora: " + this.getEditora());
        System.out.println("Categoria: " + this.getArea());
        System.out.println("Ano: " + this.getAno());
        System.out.println("Valor: R$ " + this.getValor());
        System.out.println("Estoque: " + this.getQtdEstoque());
        System.out.println("Valor total em estoque: R$ " + this.getValorTotal());
        System.out.println();
    }

}
