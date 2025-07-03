package br.dcx.ufpb.jefferson.workshopmongo.services.exception;

public class ObjectNotFoundExeception extends RuntimeException {
    public ObjectNotFoundExeception(String message) {
        super(message);
    }
}
