/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Aluguel;
import java.io.Serializable;

/**
 *
 * @author daiah
 */
public class AluguelDAO <TIPO> extends DAOGenerico<Aluguel> implements Serializable {

    public AluguelDAO() {
        super();
        classePersistente = Aluguel.class;
    }
}
