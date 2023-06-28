package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.endereco.Endereco;
import med.voll.api.medico.CadastroMedico;
import med.voll.api.medico.ListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid CadastroMedico dados) {
       repository.save(new Medico(dados));
    }

    @GetMapping
    public List<ListagemMedico> listar(){
        return repository.findAll().stream().map(ListagemMedico::new).toList();
    }
}

