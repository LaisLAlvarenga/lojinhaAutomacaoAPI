package DataFactory;

import pojo.ComponentePojo;
import pojo.ProdutoPojo;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDataFactory {
    public static ProdutoPojo criarProdutoComumComValorIgualA(double valor) {
        // Retorna um produto do tipo ProdutoPojo, com os mesmos dados. Variando apenas o valor.

        ProdutoPojo produto = new ProdutoPojo();

        produto.setProdutoNome("Produto Teste");
        produto.setProdutoValor(0.00);

        List<String> cores = new ArrayList<>();
        cores.add("Preto");
        cores.add("Branco");
        produto.setProdutoCores(cores);

        produto.setProdutoUrlMock("");

        List<ComponentePojo> componentes = new ArrayList<>();

        ComponentePojo componente = new ComponentePojo();
        componente.setComponenteNome("Controle");
        componente.setComponenteQuantidade(1);
        componentes.add(componente);

        ComponentePojo componenteDois = new ComponentePojo();
        componenteDois.setComponenteNome("Controle Dois");
        componenteDois.setComponenteQuantidade(2);
        componentes.add(componenteDois);

        produto.setComponentes(componentes);

        return produto;
    }
}
