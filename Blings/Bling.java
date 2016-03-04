package ducksim.Blings;

import java.awt.Color;

import ducksim.State;
import ducksim.behaviors.FlyBehavior.FlyBehavior;
import ducksim.behaviors.QuackBehavior.QuackBehavior;
import ducksim.ducks.Duck;

public abstract class Bling extends Duck {
	
	protected Duck duck;

	public Bling(Duck duck) {
		this.duck = duck;
	}
	
	public void swim() {
		duck.swim();
	}
    
    public void quack() {
        duck.quack();
    }
    
    public String getQuack() {
        return duck.getQuack();
    }
    
    public void fly() {
        duck.fly();
    }
    
    public State getState() {
        return duck.getState();
    }
    
    public void setState(State state) {
        duck.setState(state);
    }
    
    public void setColor(Color c) {
        duck.setColor(c);
    }
    
    public Color getColor() {
        return duck.getColor();
    }
    
    public void setQuackBehavior(QuackBehavior quackBehavior) {
    	duck.setQuackBehavior(quackBehavior);
    }
    
    public void setFlyBehavior(FlyBehavior flyBehavior) {
    	duck.setFlyBehavior(flyBehavior);
    }
    
    // join / quit and capture / release commands
    
    public void joinDSWC() {
        duck.joinDSWC();
    }
    
    public void quitDSWC() {
        duck.quitDSWC();
    }
    
    public boolean isOnDSWC() {
        return duck.isOnDSWC();
    }
    
    public void capture() {
        duck.capture();
    }
    
    public void release() {
        duck.release();
    }
    
    public boolean isFree() {
        return duck.isFree();
    }
    
    public boolean isWelcoming() {
    	return duck.isWelcoming();
    }
    
    public void welcomes() {
    	duck.welcomes();
    }
    
    public void unWelcome() {
    	duck.unWelcome();
    }
    
    public void setWelcomeMsg(String msg) {
    	duck.setWelcomeMsg(msg);
    }
    
    public String getWelcomeMsg() {
    	return duck.getWelcomeMsg();
    }
	
}
