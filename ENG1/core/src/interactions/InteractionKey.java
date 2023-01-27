package interactions;
import food.FoodItem.FoodID;
import stations.Station.StationID;

public class InteractionKey {
    public final FoodID x;
    public final StationID y;

    public InteractionKey(FoodID x, StationID y) {
        this.x = x;
        this.y = y;
    }
}
