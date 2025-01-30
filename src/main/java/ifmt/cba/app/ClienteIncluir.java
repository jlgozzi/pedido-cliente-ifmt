package ifmt.cba.app;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import ifmt.cba.dto.BairroDTO;
import ifmt.cba.dto.ClienteDTO;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ClienteIncluir {
    public static void main(String[] args) {

        String codigo = JOptionPane.showInputDialog("Forneca o codigo do bairro do cliente");
        Response response = RestAssured.get("http://localhost:8080/bairro/codigo/" + codigo);

        if (response.getStatusCode() == 200) {
            Gson gson = new Gson();
            BairroDTO bairroDTO = gson.fromJson(response.getBody().asString(), BairroDTO.class);

            ClienteDTO clienteDTO = new ClienteDTO();

            clienteDTO.setNome(JOptionPane.showInputDialog("Forneca o nome do cliente"));
            clienteDTO.setCPF(JOptionPane.showInputDialog("Forneca o CPF do cliente"));
            // exatamente nesse padrão: 999.999.999-99
            clienteDTO.setRG(JOptionPane.showInputDialog("Forneca o RG do cliente"));
            // exatamente nesse padrão: 999999-9
            clienteDTO.setTelefone(JOptionPane.showInputDialog("Forneca o telefone do cliente"));
            // exatamente nesse padrão: 99 99999-9999
            clienteDTO.setLogradouro(JOptionPane.showInputDialog("Forneca o logradouro do cliente"));
            clienteDTO.setNumero(JOptionPane.showInputDialog("Forneca o numero do cliente"));
            clienteDTO.setPontoReferencia(JOptionPane.showInputDialog("Forneca o ponto de referencia do cliente"));
            clienteDTO.setBairro(bairroDTO);

            System.out.println(clienteDTO);

            RestAssured
                    .given()
                    // .log().all()
                    .contentType("application/json")
                    .body(clienteDTO)
                    .when()
                    .post("http://localhost:8080/cliente/");
            // .then()
            // .log().all();

        } else {
            System.out.println("Bairro não localizado");
        }

    }
}
