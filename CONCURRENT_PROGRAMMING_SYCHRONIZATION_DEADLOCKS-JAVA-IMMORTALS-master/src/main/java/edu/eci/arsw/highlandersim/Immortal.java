package edu.eci.arsw.highlandersim;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Immortal extends Thread {

    private ImmortalUpdateReportCallback updateCallback=null;
    
    private int health;
    
    private int defaultDamageValue;

    private final List<Immortal> immortalsPopulation;

    private final String name;

    private final Random r = new Random(System.currentTimeMillis());
    
    private boolean vivo= true;
    
    private Object objeto= new Object();
    
    private ArrayList<Integer> muertos= new ArrayList<Integer>();
    
    


    public Immortal(String name, List<Immortal> immortalsPopulation, int health, int defaultDamageValue, ImmortalUpdateReportCallback ucb) {
        super(name);
        this.updateCallback=ucb;
        this.name = name;
        this.immortalsPopulation = immortalsPopulation;
        this.health = health;
        this.defaultDamageValue=defaultDamageValue;
    }

    public void run() {

        while (true) {
        	while(vivo) { 
        		Immortal im;

                int myIndex = immortalsPopulation.indexOf(this);

                
                
                int	nextFighterIndex = r.nextInt(immortalsPopulation.size());
                              

                //avoid self-fight
                while(true) {
                	if (nextFighterIndex == myIndex || muertos.contains(nextFighterIndex)) {
                		nextFighterIndex = ((nextFighterIndex + 1) % immortalsPopulation.size());
                	}else {
                		break;
                	}
                }
                im = immortalsPopulation.get(nextFighterIndex);

                this.fight(im);

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        	}
        	synchronized (objeto) {
				try {
					objeto.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

        }

    }

    public void fight(Immortal i2) {

    	
    	synchronized (immortalsPopulation) {
    		if (i2.getHealth() > 0) {
                i2.changeHealth(i2.getHealth() - defaultDamageValue);
                this.health += defaultDamageValue;
                updateCallback.processReport("Fight: " + this + " vs " + i2+"\n");
            } else {
                updateCallback.processReport(this + " says:" + i2 + " is already dead!\n");
                muertos.add(immortalsPopulation.indexOf(i2));
            }
		}
    }

    public void changeHealth(int v) {
        health = v;
    }

    public int getHealth() {
        return health;
    }

    public void pause() {
    	vivo=false;
    }
    
    public void resume1() {
    	vivo=true;
    	synchronized (objeto) {
			objeto.notifyAll();
		}
    }
    
    
    @Override
    public String toString() {

        return name + "[" + health + "]";
    }

}
