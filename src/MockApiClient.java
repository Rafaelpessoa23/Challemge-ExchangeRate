import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class MockApiClient {

    private final HttpClient client = HttpClient.newHttpClient();


    public String getExchangeRateJson(String baseCurrency, String targetCurrency) {

        String apiKey = "100d78a5947a0f838fe5af72";
        String apiUrl = "https://v6.exchangerate-api.com/v6/" + apiKey + "/pair/" + baseCurrency + "/" + targetCurrency;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .GET()
                .header("Accept", "application/json")
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body(); // Retorna o corpo da resposta JSON
            } else {
                System.err.println("Erro na requisição da API: " + response.statusCode());
                // Você pode querer logar mais detalhes, como response.body() para ver o erro da API.
                return null;
            }
        } catch (IOException e) {
            System.err.println("Erro de I/O ao conectar na API: " + e.getMessage());
            return null;
        } catch (InterruptedException e) {
            System.err.println("A requisição da API foi interrompida: " + e.getMessage());
            Thread.currentThread().interrupt(); // Restaura o status de interrupção
            return null;
        }
    }
}
