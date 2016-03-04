package ducksim.goose;

import ducksim.ducks.Duck;

public class GooseAdapter extends Duck {
	
	private Goose goose;
	
	
	
	public GooseAdapter(Goose goose) {
		this.goose = goose;
		setFlyBehavior(Goose.FLY_BEHAVIOR);
		setQuackBehavior(Goose.QUACK_BEHAVIOR);
        setColor(Goose.COLOR);
	}

    public String getQuack() {
        return goose.getHonk();
    }
    
    public String display() {
    	return goose.getName();
    }
    
}
