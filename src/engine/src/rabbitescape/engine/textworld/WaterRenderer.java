package rabbitescape.engine.textworld;

import rabbitescape.engine.WaterRegion;
import rabbitescape.engine.state.water.WaterCommon;

public class WaterRenderer
{
    public static
        void
        render( Chars chars, Iterable<WaterRegion> waterTable )
    {
        for ( WaterRegion waterRegion : waterTable )
        {
            if ( waterRegion.state instanceof WaterCommon )
            {
                ( (WaterCommon)waterRegion.state ).render( chars, waterRegion );
            }else
            {
                throw new AssertionError(
                    "Unknown WaterRegion state: " + waterRegion.state );
            }
        }
    }
}
