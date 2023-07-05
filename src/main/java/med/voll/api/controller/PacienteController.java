package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.paciente.*;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @GetMapping
    public ResponseEntity<Page<ListagemPaciente>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable){
        var page = repository.findByAtivoTrue(pageable).map(ListagemPaciente::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid CadastroPaciente dados, UriComponentsBuilder uriBuilder) {
        var paciente = new Paciente((dados));
        repository.save(paciente);

        var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri() ;

        return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid AtualizarPaciente dados) {
        var paciente = repository.getReferenceById(dados.id());
        paciente.atualizarInformações(dados);

        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        paciente.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detelhar(@PathVariable Long id){
        var paciente = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
    }
}
