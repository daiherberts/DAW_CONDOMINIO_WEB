/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.PessoaDAO;
import br.edu.ifsul.modelo.Pessoa;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author daiah
 */
@ManagedBean(name = "controlePessoa")
@SessionScoped
public class ControlePessoa implements Serializable {

    private PessoaDAO dao;
    private Pessoa obj;

    public ControlePessoa() {
        dao = new PessoaDAO();
    }

    public void setDao(PessoaDAO dao) {
        this.dao = dao;
    }

    public Pessoa getObj() {
        return obj;
    }

    public void setObj(Pessoa obj) {
        this.obj = obj;
    }

    //ações
    
    public String listar(){
        return "/privado/pessoa/listar?faces-redirect=true";
    }
    
    public String novo(){
        obj = new Pessoa();
        return "formulario?faces-redirect=true";
    }
    public String salvar(){
        if(dao.salvar(obj)){
            Util.mensagemInformacao(dao.getMensagem());
            return "listar?faces-redirect=true";
    }else{
        Util.mensagemErro(dao.getMensagem()); //Criar método para erros
        return "formulario?faces-redirect=true";
}
    }
    
    public String cancelar(){
        return "listar?faces-redirect=true";
    }
    
    public String editar(Integer id){
        obj = dao.localizar(id);
        return "formulario?faces-redirect=true";
    }
    public void remover (Integer id){
        obj = dao.localizar(id);
        if (dao.remover(obj)){
        Util.mensagemInformacao(dao.getMensagem());
    } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }
}



