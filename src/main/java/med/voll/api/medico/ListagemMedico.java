package med.voll.api.medico;

import org.springframework.web.bind.annotation.PutMapping;

public record ListagemMedico(Long id, String nome, String email, String crm, Especialidade especialidade) {
    public ListagemMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }


}


