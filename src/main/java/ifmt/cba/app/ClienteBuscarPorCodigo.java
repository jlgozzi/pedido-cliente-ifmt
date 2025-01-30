package ifmt.cba.app;

import javax.swing.JOptionPane;
import com.google.gson.Gson;
import ifmt.cba.dto.ClienteDTO;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ClienteBuscarPorCodigo {

    public static void main(String[] args) {

        String codigo = JOptionPane.showInputDialog("Forneca o codigo do Cliente");
        Response response = RestAssured.get("http://localhost:8080/cliente/codigo/" + codigo);

        // acessando o JSON
        JsonPath jsonPath = new JsonPath(response.asString());
        System.out.println("Codigo: " + jsonPath.getInt("codigo"));

        // Transformando o JSON em entidade DTO
        Gson gson = new Gson();
        ClienteDTO clienteDTO = gson.fromJson(response.getBody().asString(), ClienteDTO.class);
        System.out.println("----------------------------------------");
        System.out.println("Codigo: " + clienteDTO.getCPF());
        System.out.println("Nome: " + clienteDTO.getNome());

        // System.out.println("Nome..: " + ClienteDTO.getNome());
    }

}
