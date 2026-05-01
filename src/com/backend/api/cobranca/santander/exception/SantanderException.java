package com.backend.api.cobranca.santander.exception;

/**
 * ExceÃ§Ã£o a ser lanÃ§ada na ocorrÃªncia de falhas provenientes da Nota Fiscal
 * Eletronica.
 */
public class SantanderException extends Exception {

    private static final long serialVersionUID = -5054900660251852366L;

    String message;

    /**
     * Construtor da classe.
     *
     * @param e
     */
    public SantanderException(Throwable e) {
        super(e);
    }

    public SantanderException(Exception e) {
        super(e);
    }

    /**
     * Construtor da classe.
     *
     * @param message
     */
    public SantanderException(String message) {
        this((Throwable) null);
        this.message = message;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
