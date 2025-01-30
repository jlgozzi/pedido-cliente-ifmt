package ifmt.cba.app;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import ifmt.cba.dto.GrupoAlimentarDTO;
import ifmt.cba.dto.ProdutoDTO;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ProdutoIncluir {
    public static void main(String[] args) {

        String codigo = JOptionPane.showInputDialog("Forneca o codigo do grupo de produto");
        Response response = RestAssured.get("http://localhost:8080/grupoalimentar/codigo/" + codigo);

        if (response.getStatusCode() == 200) {
            Gson gson = new Gson();
            GrupoAlimentarDTO grupoAlimentarDTO = gson.fromJson(response.getBody().asString(), GrupoAlimentarDTO.class);

            ProdutoDTO produtoDTO = new ProdutoDTO();
            produtoDTO.setNome(JOptionPane.showInputDialog("Forneca o nome do produto"));
            produtoDTO.setCustoUnidade(Integer.parseInt(JOptionPane.showInputDialog("Forneca o custo por unidade do produto")));
            produtoDTO.setEstoque(Integer.parseInt(JOptionPane.showInputDialog("Forneca o estoque do produto")));
            produtoDTO.setEstoqueMinimo(Integer.parseInt(JOptionPane.showInputDialog("Forneca o estoque minimo do produto")));
            produtoDTO.setValorEnergetico(Integer.parseInt(JOptionPane.showInputDialog("Forneca o valor energetico do produto")));
            produtoDTO.setGrupoAlimentar(grupoAlimentarDTO);

            RestAssured
            .given()
                //.log().all()
                .contentType("application/json")
                .body(produtoDTO)
            .when()
                .post("http://localhost:8080/produto/");
            //.then()
            //  .log().all();
             
        } else {
            System.out.println("Grupo Alimentar não localizado");
        }
    }

}
