package rabbitescape.engine.state.water;

import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.WaterRegion;
import rabbitescape.engine.textworld.Chars;

public class WaterRegionEmptyState extends WaterCommon{
    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars ) {}

    @Override
    public void render( Chars chars, WaterRegion waterRegion ) {}

    @Override
    public String name()
    {
        return "WATER_REGION_EMPTY";
    }
}
