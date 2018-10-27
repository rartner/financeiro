package br.com.academiadev.financeiro.endpoint;

import br.com.academiadev.financeiro.model.Usuario;
import br.com.academiadev.financeiro.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/usuario")
public class UsuarioEndpoint {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/")
    public void save(@RequestBody Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @GetMapping("/")
    public List<Usuario> buscarUsuarios() {
        return toList(usuarioRepository.findAll());
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable("id") Long id) {
        usuarioRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    public Usuario buscar(@PathVariable("id") Long id) {
        return usuarioRepository.findById(id).orElse(new Usuario());
    }

    public <E> List<E> toList(Iterable<E> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
