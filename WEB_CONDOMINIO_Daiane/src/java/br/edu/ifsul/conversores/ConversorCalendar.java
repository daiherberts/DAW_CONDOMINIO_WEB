/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.conversores;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author daiah
 */
@FacesConverter(value = "converterCalendar")
public class ConversorCalendar implements Serializable, Converter{
    
    SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

    //converte a data da visao para Calendar
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String stringData) {
        if(stringData !=null){
           
            try { Calendar dt = Calendar.getInstance();
                dt.setTime(formatoData.parse(stringData));
                return dt;
            } catch (ParseException ex) {
                Logger.getLogger(ConversorCalendar.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }else{
            return null;
        }
    }

    //converte Calendar para string
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Calendar dt = (Calendar) o;
        return formatoData.format(dt.getTime());
    }
    
}
