/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.login;

import domen.Zaposleni;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author smulj
 */
public class LogInSO extends ApstraktnaGenerickaOperacija {

    Zaposleni zaposleni;
    
    @Override
    protected void preduslovi(Object param) throws Exception{
        if(param==null || !(param instanceof Zaposleni )){
            throw new Exception("Ne moze da se uloguje//poruke iz dokumentacije");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception{
        List<Zaposleni> sviZaposleni= broker.getAll((Zaposleni) param,null);
        System.out.println("KLASA LogInSO "+sviZaposleni); 
        
        for(Zaposleni z: sviZaposleni){
            if(z.equals((Zaposleni) param)){
                zaposleni=z;
                return;
            }
        }
        zaposleni=null;
   }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }
    
    
    
}
