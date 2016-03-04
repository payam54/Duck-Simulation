package ducksim;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ducksim.ducks.Duck;
import ducksim.ducks.DuckFactory;

public class MakeDuckDialog extends JDialog {
	private static final long serialVersionUID = -1712468556293949881L;

	private DuckFactory df = DuckFactory.getInstance();
	
    // Fields
    
    // Duck panel
    private final JPanel duckPanel = new JPanel();
    private final JLabel duckLabel = new JLabel("Duck");
    private final String[] duckStrings = {"Mallard", "Redhead", "Rubber", "Decoy", "Goose"};
    private final JComboBox<String> duckOptions = new JComboBox<>(duckStrings);
    
    // Bling panel
    private final JPanel blingPanel = new JPanel();
    private int blingCount = 0;
    
    private final JButton moonMinus = new JButton("-");
    private final JButton moonPlus = new JButton("+");
    private final JButton crossMinus = new JButton("-");
    private final JButton crossPlus = new JButton("+");
    private final JButton starMinus = new JButton("-");
    private final JButton starPlus = new JButton("+");
    
    private final JLabel moon = new JLabel("Moon");
    private final JLabel cross = new JLabel("Cross");
    private final JLabel star = new JLabel("Star");
    
    private JLabel moonLbl = new JLabel("0");
    private JLabel crossLbl = new JLabel("0");
    private JLabel starLbl = new JLabel("0");

    // Button panel
    private final JPanel buttonPanel = new JPanel();
    private final JButton okayButton = new JButton("Okay");
    private final JButton cancelButton = new JButton("Cancel");

    // Stored Data
    private String duckType = "Mallard";
    private int crossCount = 0;
    private int starCount = 0;
    private int moonCount = 0;

    // Constructor
    public MakeDuckDialog(DuckSimModel model, DuckSimView view) {

        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setLocationRelativeTo(null);
        this.setSize(300, 900);

        // add duck panel
        duckPanel.add(duckLabel);
        duckOptions.addActionListener(e -> {
            JComboBox cb = (JComboBox) e.getSource();
            duckType = (String) cb.getSelectedItem();
        });
        duckPanel.add(duckOptions);
        this.add(duckPanel);

        // add bling panel
        
        blingPanel.setLayout(new GridBagLayout());
        
        GridBagConstraints moonLayout = new GridBagConstraints();
        JPanel moonPanel = new JPanel();
        moonPanel.add(moonMinus);
        moonPanel.add(moonLbl);
        moonPanel.add(moonPlus);
        moonPanel.add(moon);
        setPlus(moonPlus, moonLbl);
        setMinus(moonMinus, moonLbl);
        
        moonLayout.fill = GridBagConstraints.HORIZONTAL;
        moonLayout.gridx = 0;
        moonLayout.gridy = 0;
        
        GridBagConstraints crossLayout = new GridBagConstraints();
        JPanel crossPanel = new JPanel();
        crossPanel.add(crossMinus);
        crossPanel.add(crossLbl);
        crossPanel.add(crossPlus);
        crossPanel.add(cross);
        setPlus(crossPlus, crossLbl);
        setMinus(crossMinus, crossLbl);
        
        crossLayout.fill = GridBagConstraints.HORIZONTAL;
        crossLayout.gridx = 0;
        crossLayout.gridy = 1;
        
        GridBagConstraints starLayout = new GridBagConstraints();
        JPanel starPanel = new JPanel();
        starPanel.add(starMinus);
        starPanel.add(starLbl);
        starPanel.add(starPlus);
        starPanel.add(star);
        setPlus(starPlus, starLbl);
        setMinus(starMinus, starLbl);
        
        starLayout.fill = GridBagConstraints.HORIZONTAL;
        starLayout.gridx = 0;
        starLayout.gridy = 2;
        
        blingPanel.add(moonPanel, moonLayout);
        blingPanel.add(crossPanel, crossLayout);
        blingPanel.add(starPanel, starLayout);
                
        this.add(blingPanel);
        
        // add button panel
        cancelButton.addActionListener(e -> {
            this.dispose();
        });
        buttonPanel.add(cancelButton);
        okayButton.addActionListener(e -> {
            // makeDuckDialog
        	
        	int starCount = Integer.parseInt(starLbl.getText());
        	int moonCount = Integer.parseInt(moonLbl.getText());
        	int crossCount = Integer.parseInt(crossLbl.getText());
        	
        	Duck duck = df.createDuck(duckType, starCount, moonCount, crossCount);
        	
        	
        	/*
            Duck duck;
            switch (duckType) {
                case "Mallard":
                    duck = new MallardDuck();
                    break;
                case "Redhead":
                    duck = new RedheadDuck();
                    break;
                case "Rubber":
                    duck = new RubberDuck();
                    break;
                case "Decoy":
                	duck = new DecoyDuck();
                	break;
                default:
                    duck = null;
            }
            */
        	
        	
        	
            if (duck != null) {
                model.addNewDuck(duck);
            }
            view.repaint();
            this.dispose();
        });
        buttonPanel.add(okayButton);
        this.add(buttonPanel);
    }

    // Public Methods
    public String getDuckType() {
        return duckType;
    }
    
    // Add action listener to plus and minus buttons
    
    private void setPlus(JButton btn, JLabel lbl) {
    	btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (blingCount >= 3) return;
				int num = Integer.parseInt(lbl.getText()) + 1;
				lbl.setText(String.valueOf(num));
				blingCount++;
			}
    	});
    }
    
    private void setMinus(JButton btn, JLabel lbl) {
    	btn.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			if (blingCount <= 0) return;
    			int num = Integer.parseInt(lbl.getText()) - 1;
    			if (num >= 0) {
	    			lbl.setText(String.valueOf(num));
	    			blingCount--;
    			}
    		}
    	});
    }
}
