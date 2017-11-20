/*
 * @author Benjamin Muscato (Github: "BenjaminMuscato"). Primary author of this file, used originally in the "BitsPlease" repository.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.io.Serializable;

public class BoardSpace extends JPanel implements Serializable {
	private JLabel label;
	private Collection<Player> players;
	private String originalText;
	private static final Color BORDER_COLOR = Color.BLACK;
	private boolean isGrandmasHouse = false;
	private boolean isStartSpace = false;
	private Color spaceColor = DeckPanel.DEFAULT_COLOR;


	// ------------ //
	// Constructors //
	// ------------ //
	public BoardSpace(Color newBackgroundColor, JLabel newLabel, Player[] newPlayers){
		this.setBackground(newBackgroundColor);
		this.setBorder(BorderFactory.createLineBorder(BORDER_COLOR));

		// "spaceColor"
		spaceColor = newBackgroundColor;

		// "label" and "originalText"
		if(newLabel != null){
			label = newLabel;
			add(label); 		// Actually add the JLabel to this BoardSpace (which is just an extension of JPanel)

			originalText = newLabel.getText();
		}
		else{
			label = new JLabel("");
			originalText = new String("");
		}

		// "players"
		if(newPlayers != null){
			players = new ArrayList<Player>(newPlayers.length);
			for(Player player : newPlayers){
				this.addPlayer(player);
			}
		}
		else{
			players = new ArrayList<Player>(0);
		}

		updateText(); // Updates the JLabel text of this BoardSpace to include any Players on this space
	}
	public BoardSpace(Color backgroundColor, JLabel label){
		this(backgroundColor, label, null);
	}
	public BoardSpace(Color backgroundColor){
		this(backgroundColor, null, null);
	}
	public BoardSpace(){
		this(Color.WHITE, null, null);
	}


	// ------------- //
	// Misc. Methods //
	// ------------- //
	private void updateText(){
		String labelText = new String("<html>" + originalText);
		if(!players.isEmpty()){
			labelText = labelText + "<br>[";
			for(Player player : players){
				labelText = labelText + "<img src=\"" + BoardSpace.class.getResource(player.getToken()) + "\">, ";
			}
			labelText = labelText.substring(0, labelText.length() - 2);
			labelText = labelText + "]";
		}
		labelText = labelText + "</html>";
		//label.setFont(new Font("Dialog", Font.PLAIN, 12));
		label.setText(labelText);
	}

	// TODO
	public String toString(){
		return super.toString();
	}


	// ------------------- //
	// Getters and Setters //
	// ------------------- //
	// "players"
	public void addPlayer(Player player){
		// Check to make sure this Player isn't already here
		if(players.contains(player)){
			return;
		}

		// Now that we're sure the Player isn't already here,
		//	add them to the collection
		players.add(player);

		updateText();
	}
	public boolean removePlayer(Player player){
		boolean result = players.remove(player);
		updateText();
		return result;
	}

	// "label"
	public JLabel getLabel(){
		return label;
	}

	// "spaceColor"
	public Color getSpaceColor(){
		return spaceColor;
	}

	// "isGrandmasHouse"
	public void setAsGrandmasHouse(boolean isGrandmasHouse){
		this.isGrandmasHouse = isGrandmasHouse;
	}

	// "isStartSpace"
	public void setAsStartSpace(boolean isStartSpace){
		this.isStartSpace = isStartSpace;
	}



	// --------------- //
	// Boolean Methods //
	// --------------- //
	public boolean containsPlayer(Player player){
		return players.contains(player);
	}
	public boolean isEmpty(){
		return players.isEmpty();
	}
	public boolean isGrandmasHouse(){
		return isGrandmasHouse;
	}
	public boolean isStartSpace(){
		return isStartSpace;
	}
}
