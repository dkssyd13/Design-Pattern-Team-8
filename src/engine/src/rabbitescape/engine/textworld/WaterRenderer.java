package rabbitescape.engine.textworld;

import rabbitescape.engine.WaterRegion;
import rabbitescape.engine.state.water.WaterCommon;

// TODO : 주석 삭제
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
//            switch ( waterRegion.state )
//            {
//            case WATER_REGION: // DONE
//                chars.set( waterRegion.x, waterRegion.y, 'N', null,
//                    waterRegion.getContents() );
//                break;
//            case WATER_REGION_HALF: // DONE
//                chars.set( waterRegion.x, waterRegion.y, 'n', null,
//                    waterRegion.getContents() );
//                break;
//            case WATER_REGION_FALLING: // DONE
//                chars.set( waterRegion.x, waterRegion.y, 'n', null,
//                    waterRegion.getContents() );
//                break;
//            case WATER_REGION_EMPTY: // DONE
//                break;
//            default:
//                throw new AssertionError(
//                    "Unknown WaterRegion state: " + waterRegion.state );
//            }
        }
    }
}
