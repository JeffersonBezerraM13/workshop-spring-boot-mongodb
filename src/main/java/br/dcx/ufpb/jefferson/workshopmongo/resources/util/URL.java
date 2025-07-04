package br.dcx.ufpb.jefferson.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Classe que vai decodificar a URL da consulta
 */
public class URL {

    /**
     * Ou ele retorna o String decodificodo ou um String vazio
     * @param text
     * @return
     */
    public static String decodeParam(String text){
        try {
            return URLDecoder.decode(text,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }
}
