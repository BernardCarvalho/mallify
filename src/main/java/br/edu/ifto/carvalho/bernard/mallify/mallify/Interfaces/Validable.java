package br.edu.ifto.carvalho.bernard.mallify.mallify.Interfaces;

import java.util.List;
import java.util.Map;

public interface Validable {
    Boolean isValid();
    Map<String, List<String>> getErros();
}
