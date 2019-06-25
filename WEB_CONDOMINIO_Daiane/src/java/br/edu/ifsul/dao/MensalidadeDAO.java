/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Mensalidade;
import java.io.Serializable;

/**
 *
 * @author daiah
 */
public class MensalidadeDAO <TIPO> extends DAOGenerico<Mensalidade> implements Serializable {

    public MensalidadeDAO() {
        super();
        classePersistente = Mensalidade.class;
    }
}
