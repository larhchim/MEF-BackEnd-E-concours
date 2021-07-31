package erecrutement.finances.gov.ma.MEF.Models;

public class ResponseBean {

    private String message;
    private String status;
    private int numero;

    public ResponseBean(String message,String status,int number){

        this.setMessage(message);
        this.setStatus(status);
        this.setNumero(number);

    }

    public ResponseBean(){

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
