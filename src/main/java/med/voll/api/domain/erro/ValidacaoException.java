package med.voll.api.domain.erro;

public class ValidacaoException extends RuntimeException {

    public ValidacaoException(String mensagem){
        super(mensagem);
    }
}
