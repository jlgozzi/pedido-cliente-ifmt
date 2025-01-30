package ifmt.cba.app;

import javax.swing.JOptionPane;

import ifmt.cba.dto.GrupoAlimentarDTO;
import io.restassured.RestAssured;

public class GrupoAlimentarIncluir {
    public static void main(String[] args) {

        String nome = JOptionPane.showInputDialog("Forneca o nome do grupo de produto");
        GrupoAlimentarDTO grupoAlimentarDTO = new GrupoAlimentarDTO();
        grupoAlimentarDTO.setNome(nome);

        RestAssured
                .given()
                    //.log().all()
                    .contentType("application/json")
                    .body(grupoAlimentarDTO)
                .when()
                    .post("http://localhost:8080/grupoalimentar/");
                //.then()
                //    .log().all();
    }
}
