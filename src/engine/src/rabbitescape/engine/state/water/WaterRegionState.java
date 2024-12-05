package rabbitescape.engine.state.water;

import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.WaterRegion;
import rabbitescape.engine.textworld.Chars;

public class WaterRegionState extends WaterCommon{
    @Override
    public void render( Chars chars, WaterRegion waterRegion )
    {
        chars.set( waterRegion.x, waterRegion.y, 'N', null,
            waterRegion.getContents() );
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x, change.y, 'N' );
    }

    @Override
    public String name()
    {
        return "WATER_REGION";
    }
}
