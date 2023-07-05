package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;
import med.voll.api.domain.medico.AtualizarMedico;
import med.voll.api.domain.medico.CadastroMedico;

@Table(name= "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String cpf;

    @Embedded
    private Endereco endereco;

    private Boolean ativo;

    public Paciente(CadastroPaciente paciente) {
        this.ativo = true;
        this.nome = paciente.nome();
        this.email = paciente.email();
        this.cpf = paciente.cpf();
        this.telefone = paciente.telefone();
        this.endereco = new Endereco(paciente.endereco());
    }
    public void atualizarInformações(AtualizarPaciente paciente) {
        if (paciente.nome() != null) {
            this.nome = paciente.nome();
        }
        if (paciente.telefone() !=null){
            this.telefone = paciente.telefone();
        }
        if (paciente.endereco() != null) {
            this.endereco.atualizarInformacoesEndereco(paciente.endereco());
        }

    }

    public void excluir() {
        this.ativo = false;
    }


}
