package Customers;

import com.badlogic.gdx.math.Vector2;

public class CustomerDef {

    public Vector2 position;
    public Class<?> type;

    public CustomerDef(Vector2 position, Class<?> type)
    {
        this.position = position;
        this.type = type;
    }
}
