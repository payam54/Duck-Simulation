
package ducksim.ducks;

import java.awt.Color;

import ducksim.behaviors.FlyBehavior.FlyBehavior;
import ducksim.behaviors.FlyBehavior.FlyWithWings;
import ducksim.behaviors.QuackBehavior.NormalQuack;
import ducksim.behaviors.QuackBehavior.QuackBehavior;

public class MallardDuck extends Duck {
	
	private static final FlyBehavior FLY_BEHAVIOR = new FlyWithWings();
	private static final QuackBehavior QUACK_BEHAVIOR = new NormalQuack();
	private static final Color COLOR = Color.GREEN;

    public MallardDuck() {
    	setFlyBehavior(FLY_BEHAVIOR);
    	setQuackBehavior(QUACK_BEHAVIOR);
        setColor(COLOR);
    }
    
    @Override
    public String display() {
        return "Mallard";
    }
    
}
