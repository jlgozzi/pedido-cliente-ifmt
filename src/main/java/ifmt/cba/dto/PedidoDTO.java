package ifmt.cba.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class PedidoDTO {

    private int codigo;
    private ClienteDTO cliente;
    private LocalDate dataPedido;
    private LocalTime horaPedido;
    private LocalTime horaProducao;
    private LocalTime horaPronto;
    private LocalTime horaEntrega;
    private LocalTime horaFinalizado;
    private EstadoPedidoDTO estado;
    private List<ItemPedidoDTO> listaItens;
    private String link;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public LocalTime getHoraPedido() {
        return horaPedido;
    }

    public void setHoraPedido(LocalTime horaPedido) {
        this.horaPedido = horaPedido;
    }

    public LocalTime getHoraProducao() {
        return horaProducao;
    }

    public void setHoraProducao(LocalTime horaProducao) {
        this.horaProducao = horaProducao;
    }

    public LocalTime getHoraPronto() {
        return horaPronto;
    }

    public void setHoraPronto(LocalTime horaPronto) {
        this.horaPronto = horaPronto;
    }

    public LocalTime getHoraEntrega() {
        return horaEntrega;
    }

    public void setHoraEntrega(LocalTime horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public LocalTime getHoraFinalizado() {
        return horaFinalizado;
    }

    public void setHoraFinalizado(LocalTime horaFinalizado) {
        this.horaFinalizado = horaFinalizado;
    }

    public List<ItemPedidoDTO> getListaItens() {
        return listaItens;
    }

    public void setListaItens(List<ItemPedidoDTO> listaItens) {
        this.listaItens = listaItens;
    }

    public EstadoPedidoDTO getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedidoDTO estado) {
        this.estado = estado;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
