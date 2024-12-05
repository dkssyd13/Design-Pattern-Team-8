package rabbitescape.engine.state.water;

import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.WaterRegion;
import rabbitescape.engine.textworld.Chars;

public class WaterRegionFallingState extends WaterCommon{
    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x, change.y, 'n' );
    }

    @Override
    public void render( Chars chars, WaterRegion waterRegion )
    {
        chars.set( waterRegion.x, waterRegion.y, 'n', null,
            waterRegion.getContents() );
    }

    @Override
    public String name()
    {
        return "WATER_REGION_FALLING";
    }
}
