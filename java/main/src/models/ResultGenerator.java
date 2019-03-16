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
@Table(name = "RESULT_GENERATORS")
public class ResultGenerator {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "ranges")
    @ElementCollection(targetClass = Integer.class)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Integer> results;
    private int max;

    public ResultGenerator() {
        max = 0;
        results = new ArrayList<>();
    }

    public ResultGenerator(@NotNull ArrayList<Integer> numbers) {
        //define the ranges and use max as a reference for where we are at
        max = 0;
        results = new ArrayList<>(numbers.size());
        for (int i = 0; i < numbers.size(); ++i) {
            int temp = max + numbers.get(i);
            max += +numbers.get(i);
            results.add(i, temp);
        }
    }

    public List<Integer> getResults() {
        return results;
    }

    public void setResults(List<Integer> results) {
        this.results = results;
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
        for (int i = 0; i < results.size(); ++i) {
            if (roll <= results.get(i))
                return i;
        }
        return 0;
    }
}
