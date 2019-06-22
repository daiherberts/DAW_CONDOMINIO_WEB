/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CondominioDAO;
import br.edu.ifsul.dao.RecursoDAO;
import br.edu.ifsul.modelo.Recurso;
import br.edu.ifsul.util.Util;
import java.io.Serializable;

/**
 *
 * @author daiah
 */
public class ControleRecurso implements Serializable{
    private RecursoDAO<Recurso> dao;
    private Recurso obj;
    private CondominioDAO daoCondominio;

    public ControleRecurso() {
        dao = new RecursoDAO();
        daoCondominio = new CondominioDAO();
    }

    public RecursoDAO<Recurso> getDao() {
        return dao;
    }

    public Recurso getObj() {
        return obj;
    }

    public void setObj(Recurso obj) {
        this.obj = obj;
    }

    public CondominioDAO getDaoCondominio() {
        return daoCondominio;
    }

    public void setDaoCondominio(CondominioDAO daoCondominio) {
        this.daoCondominio = daoCondominio;
    }
    
    public String listar(){
        return "/privado/recurso/listar?faces-redirect=true";
    }
    
    public String novo(){
        obj = new Recurso();
        return "formulario?faces-redirect=true";
    }
    
    public String salvar(){
        boolean persistiu;
        if(obj.getId()==null){
            persistiu = dao.persist(obj);
        }else {
            persistiu = dao.merge(obj);
    }
        if(persistiu){
            Util.mensagemInformacao(dao.getMensagem());
            return "listar?faces-redirect=true";
        }else {
            Util.mensagemErro(dao.getMensagem());
            return "formulario?faces-redirect=true";
        }
    }
    public String cancelar(){
        return "formulario?faces-redirect=true";
    }
    
    public String editar(Integer id){
        try{
            obj = dao.localizar(id);
            return "formulario?faces-redirect=true";
        } catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: "+Util.getMensagemErro(e));
            return "listar?faces-redirect=true";
        }
    }
    public void remover (Integer id){
        obj = dao.localizar(id);
        if(dao.remove(obj))
            Util.mensagemInformacao(dao.getMensagem());
        else
            Util.mensagemErro(dao.getMensagem());
    }



}



