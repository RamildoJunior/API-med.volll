package med.voll.api.medico;

public record ListagemMedico(String nome, String email, String crm, Especialidade especialidade) {
    public ListagemMedico(Medico medico){
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}

