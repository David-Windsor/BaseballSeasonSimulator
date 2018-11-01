import java.util.ArrayList;

/**
 * Class that houses all the information about a team
 * Right now this just includes players but could be
 * expanded to other information
 * teamValue is what we will use at first to determine wins and losses
 * this will be depricated later
 */
public class Team {
	ArrayList<Player> roster;
	double teamValue;
	String teamId;
	String teamName;
	public Team(String id, String name) {
		roster = new ArrayList<>();
		teamValue =0;
		teamId = id;
		teamName = name;
	}
	
	public void setTeamValue(double val) {
		teamValue = val;
	}
	public double getTeamValue() {
		return teamValue;
	}
	public void addPlayer(Player p) {
		roster.add(p);
	}
	
	public ArrayList<Player> getRoster(){
		return roster;
	}
	
}
