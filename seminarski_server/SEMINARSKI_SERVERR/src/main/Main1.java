/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import forme.ServerskaForma;

/**
 *
 * @author smulj
 */
public class Main1 {
     public static void main(String[] args) {
        ServerskaForma sf=new ServerskaForma();
        sf.setVisible(true);
        System.out.println("Aktivne niti: " + Thread.activeCount());

    }
}
