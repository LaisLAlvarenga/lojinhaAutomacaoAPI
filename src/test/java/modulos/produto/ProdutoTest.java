package modulos.produto;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API Rest do módulo de Produto.")
public class ProdutoTest {
    @Test
    @DisplayName("Validar os limites proibidos do valor de Produto.")
    public void testValidarLimitesProibidosValorProduto() {
       // Configurando os dados da API Rest da Lojinha.
        baseURI = "http://165.227.93.41";
        //port = 8080;
        basePath = "/lojinha";

        // Obter o token do usuário logado.
        String token = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"usuarioLogin\": \"admin\",\n" +
                        "    \"usuarioSenha\": \"admin\"\n" +
                        "}")
            .when()
                .post("/v2/login")
            .then()
                .extract()
                .path("data.token");
        //System.out.println(token);

        //Tentar inserir um novo produto com valor = 0.00. E validar a mensagem de erro retornada.
            //Checar o statusCode = 422

       given()
            .contentType(ContentType.JSON)
            .header("token", token)
            .body("{\n" +
                    "  \"produtoNome\": \"QA 02\",\n" +
                    "  \"produtoValor\": 0.00,\n" +
                    "  \"produtoCores\": [\n" +
                    "    \"Branco\", \"Preto\", \"Vermelho\"\n" +
                    "  ],\n" +
                    "  \"produtoUrlMock\": \"\",\n" +
                    "  \"componentes\": [\n" +
                    "    {\n" +
                    "      \"componenteNome\": \"teste 1\",\n" +
                    "      \"componenteQuantidade\": 1\n" +
                    "    }\n" +
                    "  ]\n" +
                    "}")
        .when()
            .post("/v2/produtos")
        .then()
            .assertThat()
               .body("error", equalTo("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00"))
               .statusCode(422);

    }
}
