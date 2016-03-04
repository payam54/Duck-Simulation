package ducksim.behaviors.FlyBehavior;

import ducksim.State;

public class FlyNoWay implements FlyBehavior {

	@Override
	public State fly() {
		return State.SWIMMING;
	}

}
