package app.service;

import app.Usuario;
import app.entity.DadosSensiveisEntity;
import app.entity.TabelaPonte;
import app.entity.UsuarioEntity;
import app.persistence.DadosSensiveisRepository;
import app.persistence.TabelaPonteRepository;
import app.persistence.UsuarioRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    private final TabelaPonteRepository tabelaPonteRepository;

    private final DadosSensiveisRepository dadosSensiveisRepository;

    public UsuarioService(UsuarioRepository repository, TabelaPonteRepository tabelaPonteRepository, DadosSensiveisRepository dadosSensiveisRepository) {
        this.repository = repository;
        this.tabelaPonteRepository = tabelaPonteRepository;
        this.dadosSensiveisRepository = dadosSensiveisRepository;
    }

    public Usuario findUsuarioByIdComDados(Long id) {
        var dadosSensiveis = new DadosSensiveisEntity();
        final var usuario = repository.findById(id);
        final var tabelaPonte = tabelaPonteRepository.findTabelaPonteByUsuarioEntityId(id);
        if(tabelaPonte.isPresent()) {
            final var ponte = tabelaPonte.get();
            if(ponte.getDadosSensiveisId() != null) {
                Optional<DadosSensiveisEntity> dadosSensiveisEntity = dadosSensiveisRepository.findById(ponte.getDadosSensiveisId());
                if(dadosSensiveisEntity.isPresent()) {
                    dadosSensiveis = dadosSensiveisEntity.get();
                }
            }
        }
        if(usuario.isPresent()) {
            return Usuario.fromEntity(usuario.get(), dadosSensiveis);
        }
        throw new EntityNotFoundException();
    }

    public Usuario findUsuarioById(Long id) {
        final var usuario = repository.findById(id);
        if(usuario.isPresent()) {
            return Usuario.fromEntity(usuario.get(), null);
        }
        throw new EntityNotFoundException();
    }

    public Long createUsuario(Usuario usuario) {
        final var usuarioEntity = new UsuarioEntity();
        final var dadosSensiveisEntity = new DadosSensiveisEntity();

        usuarioEntity.setUsername(usuario.getUsername());
        final var dados = usuario.getDadosSensiveis();
        dadosSensiveisEntity.setCpf(dados.getCpf());
        dadosSensiveisEntity.setEmail(dados.getEmail());
        dadosSensiveisEntity.setEndereço(dados.getEndereço());
        dadosSensiveisEntity.setGenero(dados.getGenero());
        dadosSensiveisEntity.setRg(dados.getRg());
        dadosSensiveisEntity.setTelefone(dados.getTelefone());
        dadosSensiveisEntity.setDataDeNascimento(dados.getDataDeNascimento());
        dadosSensiveisEntity.setLocalDeNascimento(dados.getLocalDeNascimento());
        final var id = repository.save(usuarioEntity).getId();
        final var dadosId = dadosSensiveisRepository.save(dadosSensiveisEntity).getId();

        final var tabelaPonte = new TabelaPonte();
        tabelaPonte.setUsuarioEntityId(id);
        tabelaPonte.setDadosSensiveisId(dadosId);
        tabelaPonteRepository.save(tabelaPonte);
        return id;
    }

    public void deleteDados(Long id) {
        final var tabelaPonte = tabelaPonteRepository.findTabelaPonteByUsuarioEntityId(id);
        if(tabelaPonte.isPresent()) {
            final var ponte = tabelaPonte.get();
            final var dadosSensiveisId = ponte.getDadosSensiveisId();
            if(dadosSensiveisId != null) {
                dadosSensiveisRepository.deleteById(dadosSensiveisId);
            }
            ponte.setDadosSensiveisId(null);
            tabelaPonteRepository.save(ponte);
        }
    }

    public void updateUsuario(Usuario usuario) {
        final var usuarioEntityOptional = repository.findById(usuario.getId());
        if(usuarioEntityOptional.isEmpty()) {
            throw new EntityNotFoundException();
        }
        final var dadosSensiveisEntity = new DadosSensiveisEntity();
        final var usuarioEntity = usuarioEntityOptional.get();
        usuarioEntity.setId(usuario.getId());
        usuarioEntity.setUsername(usuario.getUsername());
        final var dados = usuario.getDadosSensiveis();
        dadosSensiveisEntity.setCpf(dados.getCpf());
        dadosSensiveisEntity.setEmail(dados.getEmail());
        dadosSensiveisEntity.setEndereço(dados.getEndereço());
        dadosSensiveisEntity.setGenero(dados.getGenero());
        dadosSensiveisEntity.setRg(dados.getRg());
        dadosSensiveisEntity.setTelefone(dados.getTelefone());
        dadosSensiveisEntity.setDataDeNascimento(dados.getDataDeNascimento());
        dadosSensiveisEntity.setLocalDeNascimento(dados.getLocalDeNascimento());
        final var id = repository.save(usuarioEntity).getId();
        final var dadosId = dadosSensiveisRepository.save(dadosSensiveisEntity).getId();

        tabelaPonteRepository.findTabelaPonteByUsuarioEntityId(id).ifPresent(tabelaPonte -> {
            tabelaPonte.setDadosSensiveisId(dadosId);
            tabelaPonteRepository.save(tabelaPonte);
        });

    }
}
