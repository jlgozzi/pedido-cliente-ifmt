package ifmt.cba.app;

import javax.swing.JOptionPane;
import com.google.gson.Gson;
import ifmt.cba.dto.GrupoAlimentarDTO;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GrupoAlimentarBuscarPorCodigo {

    public static void main(String[] args) {

        String codigo = JOptionPane.showInputDialog("Forneca o codigo do grupo de produto");
        Response response = RestAssured.get("http://localhost:8080/grupoalimentar/codigo/"+codigo);
        
        //acessando o JSON
        JsonPath jsonPath = new JsonPath(response.asString());
        System.out.println("Codigo: "+jsonPath.getInt("codigo"));
        System.out.println("Nome..: "+jsonPath.getString("nome"));

        //Transformando o JSON em entidade DTO
        Gson gson = new Gson();
        GrupoAlimentarDTO grupoAlimentarDTO = gson.fromJson(response.getBody().asString(), GrupoAlimentarDTO.class);
        System.out.println("----------------------------------------");
        System.out.println("Codigo: "+grupoAlimentarDTO.getCodigo());
        System.out.println("Nome..: "+grupoAlimentarDTO.getNome());
    }

}
