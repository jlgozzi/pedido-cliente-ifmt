package ifmt.cba.app;

import javax.swing.JOptionPane;
import com.google.gson.Gson;
import ifmt.cba.dto.PedidoDTO;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PedidoBuscarPorCodigo {

    public static void main(String[] args) {

        String codigo = JOptionPane.showInputDialog("Forneca o codigo do pedido");
        Response response = RestAssured.get("http://localhost:8080/pedido/codigo/" + codigo);

        // acessando o JSON
        JsonPath jsonPath = new JsonPath(response.asString());
        System.out.println("Codigo: " + jsonPath.getInt("codigo"));

        // Transformando o JSON em entidade DTO
        Gson gson = new Gson();
        PedidoDTO pedidoDTO = gson.fromJson(response.getBody().asString(), PedidoDTO.class);
        System.out.println("----------------------------------------");
        System.out.println("Codigo: " + pedidoDTO.getCodigo());
        // System.out.println("Nome..: " + pedidoDTO.getNome());
    }

}
