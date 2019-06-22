/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;


import br.edu.ifsul.dao.LocatarioDAO;
import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.modelo.Locatario;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author daiah
 */
@ManagedBean(name = "controleLocatario")
@SessionScoped
public class ControleLocatario implements Serializable{
    private LocatarioDAO<Locatario> dao;
    private Locatario obj;
    private PessoaDAO daoPessoa;

    public ControleLocatario() {
        dao = new LocatarioDAO();
        daoPessoa = new PessoaDAO();
    }

    public LocatarioDAO<Locatario> getDao() {
        return dao;
    }

    public Locatario getObj() {
        return obj;
    }

    public void setObj(Locatario obj) {
        this.obj = obj;
    }

    public PessoaDAO getDaoPessoa() {
        return daoPessoa;
    }

    public void setDaoPessoa(PessoaDAO daoPessoa) {
        this.daoPessoa = daoPessoa;
    }
    
    public String listar(){
        return "/privado/locatario/listar?faces-redirect=true";
    }
    
    public String novo(){
        obj = new Locatario();
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

