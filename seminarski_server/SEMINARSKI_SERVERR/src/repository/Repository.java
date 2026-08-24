/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;

/**
 *
 * @author smulj
 */
public interface Repository<T> {
    //genericke metode vezane za objekat, odredjen entitet u bazi
    List<T> getAll(); //vrati sve objekte tipa T iz liste
    void delete(T param) throws Exception;
    void edit(T param) throws Exception;
    void add(T param) throws Exception;
    List<T> getAll(T param, String uslov) throws Exception; //vraca objekte koji zadovoljavaju neki uslov
    int addReturnKey(T param) throws Exception;
}
