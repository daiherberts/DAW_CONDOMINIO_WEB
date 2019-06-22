/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.CondominioDAO;
import br.edu.ifsul.dao.RecursoDAO;
import br.edu.ifsul.modelo.Condominio;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author daiah
 */
@ManagedBean(name = "controleCondominio")
@SessionScoped
public class ControleCondominio implements Serializable{
    
    private CondominioDAO<Condominio> dao;
    private Condominio obj;
    private RecursoDAO daoRecurso;

    public ControleCondominio() {
        dao = new CondominioDAO();
        daoRecurso = new RecursoDAO();
    }

    public CondominioDAO<Condominio> getDao() {
        return dao;
    }

    public void setDao(CondominioDAO<Condominio> dao) {
        this.dao = dao;
    }

    public Condominio getObj() {
        return obj;
    }

    public void setObj(Condominio obj) {
        this.obj = obj;
    }

    public RecursoDAO getDaoRecurso() {
        return daoRecurso;
    }

    public void setDaoRecurso(RecursoDAO daoRecurso) {
        this.daoRecurso = daoRecurso;
    }
    public String listar(){
        return "/privado/condominio/listar?faces-redirect=true";
    }
    
    public String novo(){
        obj = new Condominio();
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


