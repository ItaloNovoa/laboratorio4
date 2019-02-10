/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blacklistvalidator;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author 2108263
 */
public class ThreadBlack extends Thread{
    int ini,fin;
    String addres;
    AtomicInteger ocurrencesCount= new AtomicInteger(0);
    int checked=0;
    LinkedList<Integer> blackListOcur = new LinkedList();
    HostBlacklistsDataSourceFacade skds=HostBlacklistsDataSourceFacade.getInstance();
    
    ThreadBlack(int ini,int fin,String addres)
    {
      System.out.println("my thread created" + this);
      this.ini=ini;
      this.fin=fin;
      this.addres=addres;    
    }
    
   public void run()
   {
       for (int i=ini;i<fin && ocurrencesCount.get()<5;i++){  
           checked++;
            if (skds.isInBlackListServer(i, addres)){
                blackListOcur.add(i); 
                ocurrencesCount.incrementAndGet();
            }
        }
   }

    public LinkedList<Integer> getBlackListOcur() {
        return blackListOcur;
    }
   
   public AtomicInteger getOcurrencesCount() {
        return ocurrencesCount;
   }
    
   public int getChecked() {
        return checked;
    }

}
