package rabbitescape.engine.state.water;

import rabbitescape.engine.WaterRegion;
import rabbitescape.engine.state.State;
import rabbitescape.engine.textworld.Chars;

public abstract class WaterCommon implements State
{
    public void render( Chars chars, WaterRegion waterRegion ){
        throw new AssertionError(
            "Unknown WaterRegion state: " + waterRegion.state.name() );
    }
}
