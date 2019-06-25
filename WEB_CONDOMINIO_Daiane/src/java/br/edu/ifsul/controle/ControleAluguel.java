/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AluguelDAO;
import br.edu.ifsul.dao.LocatarioDAO;
import br.edu.ifsul.dao.UCDAO;
import br.edu.ifsul.modelo.Aluguel;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author daiah
 */
@ManagedBean(name = "controleAluguel")
@SessionScoped
public class ControleAluguel implements Serializable{
    private AluguelDAO<Aluguel> dao;
    private Aluguel obj;
    private UCDAO daoUC;
    private LocatarioDAO daoLocatario;

    public ControleAluguel() {
        dao = new AluguelDAO<Aluguel>();
        daoUC = new UCDAO();
        daoLocatario = new LocatarioDAO();
    }

    public AluguelDAO<Aluguel> getDao() {
        return dao;
    }

    public Aluguel getObj() {
        return obj;
    }

    public void setObj(Aluguel obj) {
        this.obj = obj;
    }
    
    
    
    public String listar(){
        return "/privado/aluguel/listar?faces-redirect=true";
    }
    
    public String novo(){
        obj = new Aluguel();
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

    public UCDAO getDaoUC() {
        return daoUC;
    }

    public void setDaoUC(UCDAO daoUC) {
        this.daoUC = daoUC;
    }

    public LocatarioDAO getDaoLocatario() {
        return daoLocatario;
    }

    public void setDaoLocatario(LocatarioDAO daoLocatario) {
        this.daoLocatario = daoLocatario;
    }

}



