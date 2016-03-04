package ducksim.behaviors.FlyBehavior;

import ducksim.State;

public class FlyWithWings implements FlyBehavior {

	@Override
	public State fly() {
		return State.FLYING;
	}

}
