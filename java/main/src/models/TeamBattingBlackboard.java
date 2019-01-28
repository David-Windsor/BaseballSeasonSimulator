package models;

import com.sun.istack.NotNull;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author David Windsor
 * Classs used to contain the per-inning batting information of a team. I don't have a good name for it yet
 * Rather than keeping it in the class and occupying it like crazy we can use this as a container and add methods as needed
 */
@Entity
@Table(name = "TEAM_BATTING")
class TeamBattingBlackboard {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "runs_per_inning")
    @ElementCollection(targetClass = Integer.class)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Integer> runsPerInning;
    private int max;

    public TeamBattingBlackboard() {
        max = 0;
        runsPerInning = new ArrayList<>();
    }

    TeamBattingBlackboard(@NotNull ArrayList<Integer> numbers) {
        //define the ranges and use max as a reference for where we are at
        max = 0;
        runsPerInning = new ArrayList<>(numbers.size());
        for (int i = 0; i < numbers.size(); ++i) {
            int temp = max + numbers.get(i);
            max += +numbers.get(i);
            runsPerInning.add(i, temp);
        }
    }

    public List<Integer> getRunsPerInning() {
        return runsPerInning;
    }

    public void setRunsPerInning(List<Integer> runsPerInning) {
        this.runsPerInning = runsPerInning;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    int roll() {
        Random rng = new Random();
        int roll = rng.nextInt(max + 1);
        for (int i = 0; i < runsPerInning.size(); ++i) {
            if (roll <= runsPerInning.get(i))
                return i;
        }
        return 0;
    }
}
