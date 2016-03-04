
package ducksim.ducks;

import java.awt.Color;

import ducksim.behaviors.FlyBehavior.FlyBehavior;
import ducksim.behaviors.FlyBehavior.FlyNoWay;
import ducksim.behaviors.QuackBehavior.QuackBehavior;
import ducksim.behaviors.QuackBehavior.QuackSqueek;

public class RubberDuck extends Duck {
	
	private static final FlyBehavior FLY_BEHAVIOR = new FlyNoWay();
	private static final QuackBehavior QUACK_BEHAVIOR = new QuackSqueek();
	private static final Color COLOR = Color.YELLOW;
    
    public RubberDuck() {
        setFlyBehavior(FLY_BEHAVIOR);
        setQuackBehavior(QUACK_BEHAVIOR);
        setColor(COLOR);
    }
    
    
    @Override
    public String display() {
        return "Rubber";
    }
}
