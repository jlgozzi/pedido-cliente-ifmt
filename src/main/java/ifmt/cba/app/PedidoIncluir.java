package ifmt.cba.app;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import ifmt.cba.dto.ClienteDTO;
import ifmt.cba.dto.EstadoPedidoDTO;
import ifmt.cba.dto.ItemPedidoDTO;
import ifmt.cba.dto.PedidoDTO;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class PedidoIncluir {

  // Adaptador para LocalDate
  public static class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
    private DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
      return new JsonPrimitive(date.format(formatter));
    }

    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      return LocalDate.parse(json.getAsString(), formatter);
    }
  }

  // Adaptador para LocalTime
  public static class LocalTimeAdapter implements JsonSerializer<LocalTime>, JsonDeserializer<LocalTime> {
    private DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;

    @Override
    public JsonElement serialize(LocalTime time, Type typeOfSrc, JsonSerializationContext context) {
      return new JsonPrimitive(time.format(formatter));
    }

    @Override
    public LocalTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
      return LocalTime.parse(json.getAsString(), formatter);
    }

    // Método para obter o Gson com adaptadores registrados
    public static Gson getCustomGson() {
      return new GsonBuilder()
          .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
          .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
          .create();
    }

    public static void main(String[] args) {
      // Solicitando os dados do pedido
      String codigoCliente = JOptionPane.showInputDialog("Forneça o código do cliente");

      // Criando o objeto ClienteDTO e definindo o código do cliente
      ClienteDTO clienteDTO = new ClienteDTO();
      clienteDTO.setCodigo(Integer.parseInt(codigoCliente));

      // Criando o objeto PedidoDTO e associando o cliente
      PedidoDTO pedidoDTO = new PedidoDTO();
      pedidoDTO.setCliente(clienteDTO);
      pedidoDTO.setDataPedido(LocalDate.now());
      pedidoDTO.setHoraPedido(LocalTime.now());
      pedidoDTO.setEstado(EstadoPedidoDTO.ENTREGA);

      List<ItemPedidoDTO> listaItens = new ArrayList<>();
      boolean adicionarMaisItens = true;

      while (adicionarMaisItens) {
        // Solicitando dados do item
        String codigoProdutoStr = JOptionPane.showInputDialog("Digite o código do produto:");

        // Realizando a consulta ao serviço para buscar o produto pelo código
        Response response = RestAssured
            .given()
            .pathParam("codigo", codigoProdutoStr) // Passando o código do produto
            .when()
            .get("http://localhost:8080/produto/codigo/{codigo}"); // Endereço da API

        if (response.getStatusCode() == 200) {
          // Produto encontrado, agora perguntar pela quantidade
          String quantidadeStr = JOptionPane.showInputDialog("Digite a quantidade do produto:");
          int quantidade = Integer.parseInt(quantidadeStr);

          // Criando o objeto ItemPedidoDTO e associando o produto e a quantidade
          ItemPedidoDTO itemPedidoDTO = new ItemPedidoDTO();
          itemPedidoDTO.getProduto().setCodigo(Integer.parseInt(codigoProdutoStr));
          itemPedidoDTO.setQuantidadePorcao(quantidade);

          // Adicionando o item à lista de itens
          listaItens.add(itemPedidoDTO);

          // Perguntando ao usuário se quer adicionar mais itens
          int resposta = JOptionPane.showConfirmDialog(null, "Deseja adicionar mais itens?", "Adicionar Item",
              JOptionPane.YES_NO_OPTION);
          if (resposta == JOptionPane.NO_OPTION) {
            adicionarMaisItens = false;
          }
        } else {
          // Produto não encontrado
          JOptionPane.showMessageDialog(null, "Produto com código " + codigoProdutoStr + " não encontrado.");
        }
      }

      // Associando a lista de itens ao pedido
      pedidoDTO.setListaItens(listaItens);

      // Usando o Gson personalizado
      Gson gson = getCustomGson();

      // Enviando a solicitação POST para incluir o pedido
      RestAssured
          .given()
          .contentType("application/json")
          .body(gson.toJson(pedidoDTO)) // Serializando com o Gson customizado
          .when()
          .post("http://localhost:8080/pedido/")
          .then()
          .log().all(); // Aqui você pode comentar essa linha, ela apenas loga os detalhes da resposta
    }

  }

}
