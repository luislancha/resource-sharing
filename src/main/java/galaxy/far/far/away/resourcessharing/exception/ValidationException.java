package galaxy.far.far.away.resourcessharing.exception;

public class ValidationException extends RuntimeException {

    private String mensagem;

    public ValidationException() {
        this.mensagem = "Erro na validacao";
    }

    public ValidationException(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }
}
