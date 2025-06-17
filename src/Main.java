import com.google.gson.Gson;

import java.io.IOException;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        Gson gson = new Gson(); // Instância do Gson para parsing JSON
        MockApiClient apiClient = new MockApiClient(); // Instância do nosso cliente simulado de API
        int opcao;

        do {
            System.out.println("--- Conversor de Moedas para BRL (Real Brasileiro) ---");
            System.out.println("1. Dólar Americano (USD) para BRL");
            System.out.println("2. Euro (EUR) para BRL");
            System.out.println("3. Libra Esterlina (GBP) para BRL");
            System.out.println("4. Iene Japonês (JPY) para BRL");
            System.out.println("5. Dólar Canadense (CAD) para BRL");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt(); // Lê a opção do usuário
                scanner.nextLine(); // Consome a quebra de linha restante

                String baseCurrency = "BRL";
                switch (opcao) {
                    case 1:
                        baseCurrency = "USD";
                        break;
                    case 2:
                        baseCurrency = "EUR";
                        break;
                    case 3:
                        baseCurrency = "GBP";
                        break;
                    case 4:
                        baseCurrency = "JPY";
                        break;
                    case 5:
                        baseCurrency = "CAD";
                        break;
                    case 6:
                        System.out.println("Saindo do programa. Até mais!");
                        break;
                    default:
                        System.out.println("Opção inválida. Por favor, escolha uma opção entre 1 e 6.");
                        continue; // Volta para o início do loop
                }

                if (opcao != 6) {
                    System.out.print("Digite o valor para converter de " + baseCurrency + " para BRL: ");
                    double valorEntrada = scanner.nextDouble();
                    scanner.nextLine(); // Consome a quebra de linha

                    System.out.println("Buscando taxa de câmbio para " + baseCurrency + "...");

                    // 1. Chamar o cliente da API para obter o JSON da taxa de câmbio
                    String jsonResponse = apiClient.getExchangeRateJson(baseCurrency, "BRL");

                    if (jsonResponse != null) {
                        try {
                            // 2. Usar Gson para transformar a string JSON em um objeto ExchangeRateResponse
                            ExchangeRateResponse exchangeRate = gson.fromJson(jsonResponse, ExchangeRateResponse.class);

                            // 3. Realizar a conversão
                            double valorConvertido = valorEntrada * exchangeRate.getExchangeRate();

                            System.out.println("\n--- Resultado da Conversão ---");
                            System.out.printf("Valor original: %.2f %s\n", valorEntrada, baseCurrency);
                            System.out.printf("Taxa de câmbio (%s para BRL): %.4f (última atualização: %s)\n",
                                    exchangeRate.getBaseCurrency(), exchangeRate.getExchangeRate(), exchangeRate.getLastUpdated());
                            System.out.printf("Valor convertido: %.2f BRL\n", valorConvertido);
                            System.out.println("------------------------------\n");

                        } catch (Exception e) {
                            System.err.println("Erro ao processar a resposta da API (JSON inválido ou estrutura incorreta): " + e.getMessage());
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Não foi possível obter a taxa de câmbio para " + baseCurrency + ". Tente novamente.");
                    }
                }

            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, digite um número para a opção ou para o valor.");
                scanner.nextLine(); // Limpa o buffer do scanner para evitar loop infinito
                opcao = 0; // Define uma opção inválida para continuar o loop
            } catch (Exception e) {
                System.err.println("Ocorreu um erro inesperado: " + e.getMessage());
                e.printStackTrace();
                opcao = 0; // Define uma opção inválida para continuar o loop
            }

            System.out.println(); // Linha em branco para melhor visualização
        } while (opcao != 6);

        scanner.close(); // Fecha o scanner
    }



}