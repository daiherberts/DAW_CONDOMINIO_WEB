/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.conversores;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Condominio;
import br.edu.ifsul.modelo.Mensalidade;
import br.edu.ifsul.modelo.UnidadeCondominial;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author daiah
 */
@FacesConverter(value = "converterUC")
public class ConverterUC implements Serializable, Converter{

    //converter a informação do usuário (tela) para o controle
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(string == null || string.equalsIgnoreCase("Selecione um registro")){
            return null;
        }else{
            return EntityManagerUtil.getEntityManager().find(UnidadeCondominial.class, Integer.parseInt(string));
        }
            
    }
    
    //busca as informações pelo controle e converte para mostrar na tela
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if(o == null)
            return null;
        //else
        UnidadeCondominial c = (UnidadeCondominial) o;
        return c.getId().toString();
    }
    
}
