package other;
import food.FoodItem.FoodID;
import stations.Stations;

public class InteractionKey {
    public final FoodID x;
    public final Stations.StationID y;

    public InteractionKey(FoodID x, Stations.StationID y) {
        this.x = x;
        this.y = y;
    }
}
