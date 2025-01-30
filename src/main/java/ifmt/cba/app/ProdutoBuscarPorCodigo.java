package ifmt.cba.app;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import ifmt.cba.dto.ProdutoDTO;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ProdutoBuscarPorCodigo {
    public static void main(String[] args) {

        String codigo = JOptionPane.showInputDialog("Forneca o codigo do produto");
        Response response = RestAssured.get("http://localhost:8080/produto/codigo/" + codigo);

        // acessando o JSON
        JsonPath jsonPath = new JsonPath(response.asString());
        System.out.println("Codigo: " + jsonPath.getInt("codigo"));
        System.out.println("Nome..: " + jsonPath.getString("nome"));
        System.out.println("Grupo.: " + jsonPath.getString("grupoAlimentar.nome"));

        // Transformando o JSON em entidade DTO
        Gson gson = new Gson();
        ProdutoDTO produtoDTO = gson.fromJson(response.getBody().asString(), ProdutoDTO.class);
        System.out.println("----------------------------------------");
        System.out.println("Codigo: " + produtoDTO.getCodigo());
        System.out.println("Nome..: " + produtoDTO.getNome());
        System.out.println("Grupo..: " + produtoDTO.getGrupoAlimentar().getNome());

    }

}
