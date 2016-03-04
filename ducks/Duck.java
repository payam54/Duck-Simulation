
package ducksim.ducks;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import ducksim.State;
import ducksim.behaviors.FlyBehavior.FlyBehavior;
import ducksim.behaviors.QuackBehavior.QuackBehavior;

public abstract class Duck implements Observer {
	
	protected static DuckFactory df = DuckFactory.getInstance();
    
    protected Color color = Color.BLACK;
    protected State state = State.SWIMMING;
    protected boolean isFree = true;
    protected boolean isOnDSWC = false;
    protected boolean isWelcoming = false;
    protected String welcomeMsg = "";
    
    private QuackBehavior quackBehavior;
    private FlyBehavior flyBehavior;
     
    
    // typical duck commands
    
    public void swim() {
        state = State.SWIMMING;
    }
    
    public void quack() {
        if (isFree) state = State.QUACKING;
    }
    
    public String getQuack() {
        return quackBehavior.getQuack();
    }
    
    public void fly() {
        if (isFree) state = flyBehavior.fly();
    }
    
    public State getState() {
        return state;
    }
    
    public void setState(State state) {
        this.state = state;
    }
    
    public void setColor(Color c) {
        color = c;
    }
    
    public Color getColor() {
        return color;
    }
    
    public void setQuackBehavior(QuackBehavior quackBehavior) {
    	this.quackBehavior = quackBehavior;
    }
    
    public void setFlyBehavior(FlyBehavior flyBehavior) {
    	this.flyBehavior = flyBehavior;
    }
    
    // join / quit and capture / release commands
    
    public void joinDSWC() {
    	df.addObserver(this);
        isOnDSWC = true;
    }
    
    public void quitDSWC() {
    	df.deleteObserver(this);
        isOnDSWC = false;
    }
    
    public boolean isOnDSWC() {
        return isOnDSWC;
    }
    
    public void capture() {
        isFree = false;
    }
    
    public void release() {
        isFree = true;
    }
    
    public boolean isFree() {
        return isFree;
    }
    
    public boolean isWelcoming() {
    	return isWelcoming;
    }
    
    public void welcomes() {
    	isWelcoming = true;
    }
    
    public void unWelcome() {
    	isWelcoming = false;
    }
    
    public void setWelcomeMsg(String msg) {
    	welcomeMsg = msg;
    }
    
    public String getWelcomeMsg() {
    	return welcomeMsg;
    }
    
    public abstract String display();
    
    // Observer pattern

    @Override
	public void update(Observable o, Object arg) {
		if (isOnDSWC()) {
			if (!isFree()) {
				setWelcomeMsg("Beware!");
				welcomes();
			} else {
				setWelcomeMsg("Welcome!");
				welcomes();
			}
		}
	}
    
}
