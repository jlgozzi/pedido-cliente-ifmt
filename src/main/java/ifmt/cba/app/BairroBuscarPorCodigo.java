package ifmt.cba.app;

import javax.swing.JOptionPane;
import com.google.gson.Gson;
import ifmt.cba.dto.BairroDTO;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class BairroBuscarPorCodigo {

    public static void main(String[] args) {

        String codigo = JOptionPane.showInputDialog("Forneca o codigo do Bairro");
        Response response = RestAssured.get("http://localhost:8080/bairro/codigo/" + codigo);

        // acessando o JSON
        JsonPath jsonPath = new JsonPath(response.asString());
        System.out.println("Codigo: " + jsonPath.getInt("codigo"));

        // Transformando o JSON em entidade DTO
        Gson gson = new Gson();
        BairroDTO bairroDTO = gson.fromJson(response.getBody().asString(), BairroDTO.class);
        System.out.println("----------------------------------------");
        System.out.println("Nome: " + bairroDTO.getNome());

        // System.out.println("Nome..: " + BairroDTO.getNome());
    }

}
