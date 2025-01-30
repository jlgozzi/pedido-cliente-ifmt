package ifmt.cba.dto;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CardapioDTO {

    private int codigo;
    private String nome;
    private String descricao;
    private List<PreparoProdutoDTO> listaPreparoProduto;
    private String link;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<PreparoProdutoDTO> getListaPreparoProduto() {
        return listaPreparoProduto;
    }

    public void setListaPreparoProduto(List<PreparoProdutoDTO> listaPreparoProduto) {
        this.listaPreparoProduto = listaPreparoProduto;
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
