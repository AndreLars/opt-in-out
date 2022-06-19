package app.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity(name = "TabelaPonte")
@Table(name = "tabela_ponte")
public class TabelaPonte {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long dadosSensiveisId;

    @Column
    private Long usuarioEntityId;

    public TabelaPonte() {
    }

    public TabelaPonte(Long dadosSensiveisId, Long usuarioEntityId) {
        this.id = id;
        this.dadosSensiveisId = dadosSensiveisId;
        this.usuarioEntityId = usuarioEntityId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDadosSensiveisId() {
        return dadosSensiveisId;
    }

    public void setDadosSensiveisId(Long dadosSensiveisId) {
        this.dadosSensiveisId = dadosSensiveisId;
    }

    public Long getUsuarioEntityId() {
        return usuarioEntityId;
    }

    public void setUsuarioEntityId(Long usuarioEntityId) {
        this.usuarioEntityId = usuarioEntityId;
    }
}
