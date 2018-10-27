package br.com.academiadev.financeiro.endpoint;

import br.com.academiadev.financeiro.model.LancamentoFinanceiro;
import br.com.academiadev.financeiro.repository.LancamentoFinanceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("lancamentoFinanceiro")
public class LancamentoFinanceiroEndpoint {

    @Autowired
    private LancamentoFinanceiroRepository repository;

    @GetMapping("/")
    public List<LancamentoFinanceiro> listar() {
        return toList(repository.findAll());
    }

    @PostMapping("/")
    public void salvar(@RequestBody LancamentoFinanceiro lancamentoFinanceiro) {
        repository.save(lancamentoFinanceiro);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable("id") Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/{id}")
    public LancamentoFinanceiro buscar(@PathVariable("id") Long id) {
        return repository.findById(id).orElse(new LancamentoFinanceiro());
    }

    private <E> List<E> toList(Iterable<E> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
