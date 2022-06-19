package app;

import app.entity.DadosSensiveisEntity;
import app.entity.UsuarioEntity;

public class Usuario {

    private Long id;
    private String username;
    private DadosSensiveis dadosSensiveis;

    public Usuario(Long id, String username, DadosSensiveis dadosSensiveis) {
        this.id = id;
        this.username = username;
        this.dadosSensiveis = dadosSensiveis;
    }

    public static Usuario fromEntity(UsuarioEntity entity, DadosSensiveisEntity sensiveisEntity) {
        final var dados = new DadosSensiveis();
        if(sensiveisEntity != null) {
            dados.setCpf(sensiveisEntity.getCpf());
            dados.setEmail(sensiveisEntity.getEmail());
            dados.setEndereço(sensiveisEntity.getEndereço());
            dados.setGenero(sensiveisEntity.getGenero());
            dados.setRg(sensiveisEntity.getRg());
            dados.setTelefone(sensiveisEntity.getTelefone());
            dados.setDataDeNascimento(sensiveisEntity.getDataDeNascimento());
            dados.setLocalDeNascimento(sensiveisEntity.getLocalDeNascimento());
        }

        return new Usuario(entity.getId(), entity.getUsername(), dados);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public DadosSensiveis getDadosSensiveis() {
        return dadosSensiveis;
    }

    public void setDadosSensiveis(DadosSensiveis dadosSensiveis) {
        this.dadosSensiveis = dadosSensiveis;
    }
}
