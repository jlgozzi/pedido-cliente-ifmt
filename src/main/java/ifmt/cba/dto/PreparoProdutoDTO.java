package ifmt.cba.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class PreparoProdutoDTO {

    private int codigo;
    private String nome;
    private ProdutoDTO produto;
    private TipoPreparoDTO tipoPreparo;
    private int tempoPreparo;
    private float valorPreparo;
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

    public ProdutoDTO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoDTO produto) {
        this.produto = produto;
    }

    public TipoPreparoDTO getTipoPreparo() {
        return tipoPreparo;
    }

    public void setTipoPreparo(TipoPreparoDTO tipoPreparo) {
        this.tipoPreparo = tipoPreparo;
    }

    public int getTempoPreparo() {
        return tempoPreparo;
    }

    public void setTempoPreparo(int tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }

    public float getValorPreparo() {
        return valorPreparo;
    }

    public void setValorPreparo(float valorPreparo) {
        this.valorPreparo = valorPreparo;
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
