package med.voll.api.domain.erro;

public class ValidacaoExpection extends RuntimeException {

    public ValidacaoExpection(String mensagem){
        super(mensagem);
    }
}
