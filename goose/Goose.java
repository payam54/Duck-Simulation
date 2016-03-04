package ducksim.goose;

import java.awt.Color;

import ducksim.behaviors.FlyBehavior.FlyBehavior;
import ducksim.behaviors.FlyBehavior.FlyWithWings;
import ducksim.behaviors.QuackBehavior.NormalQuack;
import ducksim.behaviors.QuackBehavior.QuackBehavior;


public class Goose {
	
	public static final FlyBehavior FLY_BEHAVIOR = new FlyWithWings();
	public static final QuackBehavior QUACK_BEHAVIOR = new NormalQuack();
	public static final Color COLOR = Color.WHITE;
	
	public String getHonk() {
		return "Honk!";
	}
    
    public String getName() {
		return "Goose";
	}
	
}
