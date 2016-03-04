package ducksim.ducks;

import java.awt.Color;

import ducksim.behaviors.FlyBehavior.FlyBehavior;
import ducksim.behaviors.FlyBehavior.FlyNoWay;
import ducksim.behaviors.QuackBehavior.QuackBehavior;
import ducksim.behaviors.QuackBehavior.QuackNoWay;

public class DecoyDuck extends Duck {
	
	private static final FlyBehavior FLY_BEHAVIOR = new FlyNoWay();
	private static final QuackBehavior QUACK_BEHAVIOR = new QuackNoWay();
	private static final Color COLOR = new Color(66,44,22);
	
	public DecoyDuck() {
		setFlyBehavior(FLY_BEHAVIOR);
		setQuackBehavior(QUACK_BEHAVIOR);
		setColor(COLOR);
	}

	@Override
	public String display() {
		return "Decoy";
	}

}
