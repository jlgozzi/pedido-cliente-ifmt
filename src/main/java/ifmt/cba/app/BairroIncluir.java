package ifmt.cba.app;

import javax.swing.JOptionPane;

import ifmt.cba.dto.BairroDTO;
import io.restassured.RestAssured;

public class BairroIncluir {
  public static void main(String[] args) {

    BairroDTO bairroDTO = new BairroDTO();
    bairroDTO.setNome(JOptionPane.showInputDialog("Forneca o nome do bairro"));
    bairroDTO.setCustoEntrega(Integer.parseInt(JOptionPane.showInputDialog("Forneca o custo de entrega do bairro")));

    RestAssured
        .given()
        // .log().all()
        .contentType("application/json")
        .body(bairroDTO)
        .when()
        .post("http://localhost:8080/bairro/");
    // .then()
    // .log().all();
  }
}