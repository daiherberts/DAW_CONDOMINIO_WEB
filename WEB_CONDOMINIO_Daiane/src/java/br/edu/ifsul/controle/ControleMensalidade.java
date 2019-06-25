/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.AluguelDAO;
import br.edu.ifsul.dao.MensalidadeDAO;
import br.edu.ifsul.dao.MensalidadeDAO;
import br.edu.ifsul.modelo.Aluguel;
import br.edu.ifsul.modelo.Mensalidade;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author daiah
 */
@ManagedBean(name = "controleMensalidade")
@SessionScoped
public class ControleMensalidade implements Serializable{
    private MensalidadeDAO<Aluguel> dao;
    private Mensalidade obj;
    private AluguelDAO daoAluguel;

    public ControleMensalidade() {
        dao = new MensalidadeDAO();
        daoAluguel = new AluguelDAO();
    }

    public Mensalidade getObj() {
        return obj;
    }

    public void setObj(Mensalidade obj) {
        this.obj = obj;
    }

    public AluguelDAO getDaoAluguel() {
        return daoAluguel;
    }

    public void setDaoAluguel(AluguelDAO daoAluguel) {
        this.daoAluguel = daoAluguel;
    }
    
    
    public String listar(){
        return "/privado/mensalidae/listar?faces-redirect=true";
    }
    
    public String novo(){
        obj = new Mensalidade();
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



