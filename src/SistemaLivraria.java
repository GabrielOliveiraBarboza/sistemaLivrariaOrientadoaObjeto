import java.util.List;
import java.util.Scanner;

public class SistemaLivraria {

    public static void main(String[] args) {
        Scanner tec = new Scanner(System.in);
        int opcao = -1;

        LivrariaService livrariaService = new LivrariaService();


        while (opcao != 0) {
            System.out.println("Sistema de livraria: ");
            System.out.println(" 1 – Cadastrar novo livro");
            System.out.println("2 – Listar livros");
            System.out.println("3 – Buscar livros por nome");
            System.out.println("4 – Buscar livros por categoria");
            System.out.println("5 – Buscar livros por preço");
            System.out.println("6 – Busca por quantidade em estoque");
            System.out.println("7 – Valor total no estoque");
            System.out.println("8-  Carregar estoque");
            System.out.println("9-  Atualizar arquivo de estoque");
            System.out.println("10- Criar filial");
            System.out.println("11- Estoque por filial");
            System.out.println("12- Buscar livro por código");
            System.out.println("0 – Encerrar atividades");

            System.out.println("Digite a opção desejada: ");
            opcao = tec.nextInt();
            tec.nextLine();

            if (opcao == 1) {
                System.out.print("Digite o código do livro: ");
                String codigo = tec.nextLine();
                System.out.print("Digite o título do livro: ");
                String titulo = tec.nextLine();
                System.out.print("Digite a editora do livro: ");
                String editora = tec.nextLine();
                System.out.print("Digite a área do livro: ");
                String area = tec.nextLine();
                System.out.print("Digite o ano do livro: ");
                int ano = tec.nextInt();
                System.out.print("Digite o valor do livro: ");
                double valor = tec.nextDouble();
                System.out.print("Digite a quantidade em estoque: ");
                int quantidadeEstoque = tec.nextInt();
                System.out.print("Digite o número da filial à qual o livro pertence: ");
                int codigoFilial = tec.nextInt();
                tec.nextLine();
                if (livrariaService.verificarExistenciaFilial(codigoFilial)) {
                    Filial filial = livrariaService.obterFilialPorCodigo(codigoFilial);
                    Livros novoLivro = new Livros(codigo,titulo,ano,area,editora,valor,quantidadeEstoque);
                    livrariaService.cadastrarLivro(novoLivro, filial);
                } else {
                    System.out.println("Filial não encontrada.");
                }
            }
            if (opcao == 2) {
                if (livrariaService.livros.isEmpty()) {
                    System.out.println("Nenhum livro cadastrado no sistema");
                }
                livrariaService.listarLivros();
            }
            if (opcao == 3) {
                System.out.print("Digite o nome do livro a ser buscado: ");
                String nomeBusca = tec.nextLine();
                System.out.print("Digite o número da filial onde deseja fazer a busca: ");
                int codigoFilialBusca = tec.nextInt();
                tec.nextLine();
                List<Livros> livrosPorNome = livrariaService.buscarLivrosPorTitulo(nomeBusca, codigoFilialBusca);
                if (livrosPorNome.isEmpty()) {
                    System.out.println("Livro não encontrado em estoque nesta filial!");
                }
                System.out.println();
                for (Livros livro : livrosPorNome) {
                    livro.info();
                }
            }

            if (opcao == 4) {
                System.out.print("Digite a categoria dos livros a serem buscados: ");
                String categoriaBusca = tec.nextLine();
                System.out.print("Digite o número da filial onde deseja fazer a busca: ");
                int codigoFilialBusca = tec.nextInt();
                tec.nextLine();
                List<Livros> livrosPorCategoria = livrariaService.buscarLivroPorCategoria(categoriaBusca, codigoFilialBusca);
                if (livrosPorCategoria.isEmpty()) {
                    System.out.println("Livro não encontrado!");
                }
                for (Livros livro : livrosPorCategoria) {
                    livro.info();

                }

            }
            if (opcao == 5) {
                System.out.println("Filtro por valor, digite o valor do livro que procura: ");
                System.out.println("Digite o valor minimo :");
                double valorMinimo = tec.nextDouble();
                System.out.println("Digite o valor maximo :");
                double valorMaximo = tec.nextDouble();
                System.out.print("Digite o número da filial onde deseja fazer a busca: ");
                int codigoFilialBusca = tec.nextInt();
                tec.nextLine();
                List<Livros> livroPorPreco = livrariaService.buscaPorPreco(valorMinimo, valorMaximo, codigoFilialBusca);
                for (Livros livro : livroPorPreco) {
                    livro.info();

                }

            }
            if (opcao == 6) {
                System.out.println("Digite a quantidade em estoque que busca:");
                int QtEstoque = tec.nextInt();
                System.out.print("Digite o número da filial onde deseja fazer a busca: ");
                int codigoFilial = tec.nextInt();
                List<Livros> livrosNoEstoque = livrariaService.buscaProQtEstoque(QtEstoque, codigoFilial);
                if (livrosNoEstoque.isEmpty()) {
                    System.out.println("Nenhum livro encontrado com a quantidade em estoque desejada.");
                } else {
                    for (Livros livro : livrosNoEstoque) {
                        livro.info();
                    }
                }
            }

            if (opcao == 7) {

                System.out.println("O valor total do estoque é ? R$" + livrariaService.calcularEstoque());
            }
            if (opcao == 8) {
                livrariaService.carregarDadosFiliais();
                System.out.println("Filial e estoque carregados! ");
            }
            if (opcao == 9) {
               livrariaService.salvarDadosFiliais();
            }
            if (opcao == 10) {
                System.out.println("-----Cadastrando filial----");
                System.out.println("Digite o codigo");
                int codigoFilial = tec.nextInt();
                tec.nextLine();
                System.out.println("Digite o nome");
                String nomeFilial = tec.nextLine();
                System.out.println("Digite o endereco");
                String enderecoFilial = tec.nextLine();
                System.out.println("Digite o contato");
                String contatoFilial = tec.nextLine();

                Filial novaFilial = new Filial(codigoFilial, nomeFilial, enderecoFilial, contatoFilial);
                livrariaService.criarFilial(novaFilial);

            }
            if (opcao == 11) {
                System.out.print("Digite o número da filial onde deseja fazer a busca: ");
                int codigoFilial = tec.nextInt();
                tec.nextLine();

                if (livrariaService.verificarExistenciaFilial(codigoFilial)) {
                    livrariaService.listarEstoqueFilial(codigoFilial);
                } else {
                    System.out.println("Filial não encontrada.");
                }
            }
            if (opcao == 12) {
                System.out.println("Digite o código do livro que deseja buscar: ");
                String busca = tec.next();
                List<Livros> buscaCodigo = livrariaService.buscaPorCodigo(busca);
                for (Livros livro : buscaCodigo) {
                    livro.info();
                }
            }
            if (opcao == 0) {
                System.out.println("Você encerrou o programa! ");
            }
        }
    }
}

