package ifmt.cba.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ItemOrdemProducaoDTO {
    
    private int codigo;
    private PreparoProdutoDTO preparoProduto;
    private int quantidadePorcao;
    private String link;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public PreparoProdutoDTO getPreparoProduto() {
        return preparoProduto;
    }

    public void setPreparoProduto(PreparoProdutoDTO preparoProduto) {
        this.preparoProduto = preparoProduto;
    }

    public int getQuantidadePorcao() {
        return quantidadePorcao;
    }

    public void setQuantidadePorcao(int quantidadePorcao) {
        this.quantidadePorcao = quantidadePorcao;
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
