package com.develhope.spring;

public class Acquirente {

    String AcNome;
    String AcCognome;
    String AcTelefono;
    String AcEmail;
    String Acpassword;
    String AcIndirizzo;

    public Acquirente(String acNome, String acCognome, String acTelefono, String acEmail, String acpassword) {
        AcNome = acNome;
        AcCognome = acCognome;
        AcTelefono = acTelefono;
        AcEmail = acEmail;
        Acpassword = acpassword;
    }

    public String getAcNome() {
        return AcNome;
    }

    public void setAcNome(String acNome) {
        AcNome = acNome;
    }

    public String getAcCognome() {
        return AcCognome;
    }

    public void setAcCognome(String acCognome) {
        AcCognome = acCognome;
    }

    public String getAcTelefono() {
        return AcTelefono;
    }

    public void setAcTelefono(String acTelefono) {
        AcTelefono = acTelefono;
    }

    public String getAcEmail() {
        return AcEmail;
    }

    public void setAcEmail(String acEmail) {
        AcEmail = acEmail;
    }

    public String getAcpassword() {
        return Acpassword;
    }

    public void setAcpassword(String acpassword) {
        Acpassword = acpassword;
    }

    public String getAcIndirizzo() {
        return AcIndirizzo;
    }

    public void setAcIndirizzo(String acIndirizzo) {
        AcIndirizzo = acIndirizzo;
    }

    @Override
    public String toString() {
        return "Acquirente{" +
                "AcNome='" + AcNome + '\'' +
                ", AcCognome='" + AcCognome + '\'' +
                ", AcTelefono='" + AcTelefono + '\'' +
                ", AcEmail='" + AcEmail + '\'' +
                ", Acpassword='" + Acpassword + '\'' +
                ", AcIndirizzo='" + AcIndirizzo + '\'' +
                '}';
    }
}
