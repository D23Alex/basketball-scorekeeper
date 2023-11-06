package com.d23alex.vtbstat.payload.statistics;

import com.d23alex.vtbstat.model.Player;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Performance {
    private Totals totals = new Totals();
    private Efficiency efficiency = new Efficiency();
    private Averages averages = new Averages();

    public Performance(Player player, GameEventLog gameEventLog) {
        this.totals = new Totals(player, gameEventLog);
        this.efficiency = new Efficiency(this.totals);
        this.averages = new Averages(this.totals, 1);
    }

    public Performance(Set<Player> players, GameEventLog gameEventLog) {
        var totals = players.stream().map(player -> new Totals(player, gameEventLog)).reduce(Totals::sum);
        if (totals.isEmpty())
            return;
        this.totals = totals.get();
        this.efficiency = new Efficiency(this.totals);
        this.averages = new Averages(this.totals, 1);
    }

    public Performance(Player player, List<GameEventLog> eventLogForEachGame) {
        var totals = eventLogForEachGame.stream().map(log -> new Totals(player, log)).reduce(Totals::sum);
        if (totals.isEmpty())
            return;
        this.totals = totals.get();
        this.efficiency = new Efficiency(this.totals);
        this.averages = new Averages(this.totals, eventLogForEachGame.size());
    }

    public Performance(Set<Player> players, List<GameEventLog> eventLogForEachGame) {
        List<Optional<Totals>> totalsForEachGameAndPlayer = new ArrayList<>();
        players.forEach(player -> totalsForEachGameAndPlayer.add(eventLogForEachGame.stream().map(log -> new Totals(player, log)).reduce(Totals::sum)));
        var totals = totalsForEachGameAndPlayer.stream().filter(Optional::isPresent).map(Optional::get).reduce(Totals::sum);
        if (totals.isEmpty())
            return;
        this.totals = totals.get();
        this.efficiency = new Efficiency(this.totals);
        this.averages = new Averages(this.totals, eventLogForEachGame.size());
    }
}
