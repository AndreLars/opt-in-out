package app.controller;

import app.Usuario;
import app.service.UsuarioService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.createUsuario(usuario));
    }

    @GetMapping("/dados/{id}")
    public ResponseEntity<Usuario> getUsuarioByIdComDadosSensiveis(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findUsuarioByIdComDados(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findUsuarioById(id));
    }

    @DeleteMapping("/dados/{id}")
    public ResponseEntity<Object> deleteDadosSensiveisUsuario(@PathVariable Long id) {
        usuarioService.deleteDados(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> putUsuario(@RequestBody Usuario usuario) {
        usuarioService.updateUsuario(usuario);
        return ResponseEntity.noContent().build();
    }
}