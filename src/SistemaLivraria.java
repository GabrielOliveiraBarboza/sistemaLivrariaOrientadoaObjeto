
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaLivraria {

    // aqui é a classe que ficará responsável por rodar o programa

    public static void main(String[] args)  {
        String arquivo =("src/BDLivraria.csv");
        Scanner scanner= new Scanner(System.in);
        int opcao= -1;

        LivrariaService livrariaService= new LivrariaService();
       try (BufferedReader br = new BufferedReader(new FileReader(arquivo))){
           String linha= br.readLine();
          // linha=br.readLine();
           while(linha!=null){
               String [] vet = linha.split(",");
               String codigo = vet[0];
               String titulo =vet[1];
               String editora = vet[2];
               String area= vet[3];
               int ano = Integer.parseInt(vet[4].trim());
               double valor= Double.parseDouble(vet[5].trim());
               int qtdEstoque= Integer.parseInt(vet[6].trim());
               Livros livro = new Livros (codigo,titulo,editora,area,ano,valor,qtdEstoque);
               livrariaService.cadastrarLivro(livro);
               linha = br.readLine();
           }

       }catch (IOException e){
           System.out.println("Erro");
       }
        while(opcao!=0){
            System.out.println("Sistema de livraria: ");
            System.out.println(" 1 – Cadastrar novo livro");
            System.out.println("2 – Listar livros");
            System.out.println("3 – Buscar livros por nome");
            System.out.println("4 – Buscar livros por categoria");
            System.out.println("5 – Buscar livros por preço");
            System.out.println("6 – Busca por quantidade em estoque");
            System.out.println("7 – Valor total no estoque");
            System.out.println("0 – Encerrar atividades");

            System.out.println("Digite a opção desejada: ");
            opcao= scanner.nextInt();
            scanner.nextLine();

            if(opcao==1){
                System.out.print("Digite o código do livro: ");
                String codigo = scanner.nextLine();
                System.out.print("Digite o título do livro: ");
                String titulo = scanner.nextLine();
                System.out.print("Digite a editora do livro: ");
                String editora = scanner.nextLine();
                System.out.print("Digite a área do livro: ");
                String area = scanner.nextLine();
                System.out.print("Digite o ano do livro: ");
                int ano = scanner.nextInt();
                System.out.print("Digite o valor do livro: ");
                double valor = scanner.nextDouble();
                System.out.print("Digite a quantidade em estoque: ");
                int quantidadeEstoque = scanner.nextInt();
                //Aqui eu criei uma nova instancia da classe livros para gravar todos os dados digitados do usuário
                // como argumento para o construtor da classe livros
                Livros novoLivro = new Livros(codigo, titulo, editora, area, ano, valor, quantidadeEstoque);

                livrariaService.cadastrarLivro(novoLivro);

            }
            if(opcao==2){
              if(livrariaService.livros.isEmpty()){
                  System.out.println("Nenhum livro cadastrado no sistema");
              }
                livrariaService.listarLivros();

            }
            if (opcao == 3) {
                System.out.print("Digite o nome do livro a ser buscado: ");
                String nomeBusca = scanner.nextLine();
                List<Livros> livrosPorNome = livrariaService.buscarLivrosPorTitulo(nomeBusca);
                if(livrosPorNome.isEmpty()){
                    System.out.println("Livro não encontrado!");
                }
                for (Livros livro : livrosPorNome) {
                   livro.info();
                }

            }

            if(opcao==4){
                System.out.print("Digite a categoria dos livros a serem buscados: ");
                String categoriaBusca = scanner.nextLine();
                List<Livros> livrosPorCategoria = livrariaService.buscarLivroPorCategoria(categoriaBusca);
                for (Livros livro : livrosPorCategoria) {
                    livro.info();

                }

            }
            if(opcao==5){
                System.out.println("Filtro por valor, digite o valor do livro que procura: ");
                System.out.println("Digite o valor minimo :");
                double valorMinimo= scanner.nextDouble();
                System.out.println("Digite o valor maximo :");
                double valorMaximo= scanner.nextDouble();

                List<Livros> livroPorPreco = livrariaService.buscaPorPreco(valorMinimo, valorMaximo);
                for (Livros livro : livroPorPreco) {
                    livro.info();

                }

            }
            if(opcao==6){
                System.out.println("Digite a quantidade em estoque que busca:");
                int QtEstoque= scanner.nextInt();
                List<Livros> estoque= new ArrayList<>();
                for(Livros livro: estoque){
                    livro.info();

                }

            }
            if(opcao==7){

                System.out.println("O valor total do estoque é ?" + livrariaService.calcularEstoque() );
            }
            if(opcao==0){
                System.out.println("Você encerrou o programa! ");
            }
        }

    }

}

