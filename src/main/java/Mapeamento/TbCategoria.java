package Mapeamento;
// Generated 05/06/2019 09:01:11 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TbCategoria generated by hbm2java
 */
@Entity
@Table(name="tb_categoria"
    ,schema="projeto"
)
public class TbCategoria  implements java.io.Serializable {


     private int idcat;
     private String nomecat;
     private Set<TbProduto> tbProdutos = new HashSet(0);

    public TbCategoria() {
    }

	
    public TbCategoria(int idcat) {
        this.idcat = idcat;
    }
    public TbCategoria(int idcat, String nomecat, Set<TbProduto> tbProdutos) {
       this.idcat = idcat;
       this.nomecat = nomecat;
       this.tbProdutos = tbProdutos;
    }
   
     @Id 

    
    @Column(name="idcat", unique=true, nullable=false)
    public int getIdcat() {
        return this.idcat;
    }
    
    public void setIdcat(int idcat) {
        this.idcat = idcat;
    }

    
    @Column(name="nomecat", length=50)
    public String getNomecat() {
        return this.nomecat;
    }
    
    public void setNomecat(String nomecat) {
        this.nomecat = nomecat;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="tbCategoria")
    public Set<TbProduto> getTbProdutos() {
        return this.tbProdutos;
    }
    
    public void setTbProdutos(Set<TbProduto> tbProdutos) {
        this.tbProdutos = tbProdutos;
    }




}


