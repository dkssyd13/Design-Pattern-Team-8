package rabbitescape.engine;


import rabbitescape.engine.state.State;
import rabbitescape.engine.state.fire.*;

import java.util.HashMap;
import java.util.Map;


public class Fire extends Thing
{
    public int variant;

    private final State baseVariant;

    public Fire( int x, int y, int variant )
    {
        super( x, y, stateForVariant( variant ) );
        this.variant = variant;
        baseVariant = state;
    }

    private static State stateForVariant( int variant )
    {
        return FireState.stateForVariant( variant );
//        switch ( variant ) // TODO : 주석 삭제
//        {
//        case 0:
//            return FIRE_A; // DONE
//        case 1:
//            return FIRE_B; // DONE
//        case 2:
//            return FIRE_C; // DONE
//        case 3:
//            return FIRE_D; // DONE
//        }
//        throw new RuntimeException(
//            "Variant outside expected range (0 - 3):" + variant );
    }

    @Override
    public void calcNewState( World world )
    {
        // Check if being extinguished.
        for ( WaterRegion waterRegion : world.waterTable.getItemsAt( x, y ) )
        {
            if ( waterRegion.getContents() > 0 )
            {
                state = new FireExtinguishingState();
                return;
            }
        }

        Block blockBelow = world.getBlockAt( x, y + 1 );
        // Note: when flatBelow is true may be on a slope with a flat below,
        // or sitting on the flat
        boolean flatBelow = BehaviourTools.s_isFlat( blockBelow );
        boolean still = (
                   flatBelow
                || ( world.getBlockAt( x, y ) != null )
                || BridgeTools.someoneIsBridgingAt( world, x, y )
            );
        if ( still )
        {
            Block onBlock = world.getBlockAt( x, y );
            if ( BehaviourTools.isLeftRiseSlope( onBlock ) )
            {
                state = baseVariantSwitch( 
                    new FireARiseLeftState(),
                    new FireBRiseLeftState(),
                    new FireCRiseLeftState(),
                    new FireDRiseLeftState()
                );
                return;
            }
            if ( BehaviourTools.isRightRiseSlope( onBlock ) )
            {
                state = baseVariantSwitch(
                    new FireARiseRightState(),
                    new FireBRiseRightState(),
                    new FireCRiseRightState(),
                    new FireDRiseRightState()
                );
                return;
            }
            // TODO: check here for fire falling on a bridger.
            // Fire going to a falling state may be OK
            // as bridger is burnt
            if ( flatBelow )
            {
                state = baseVariant;
                return;
            }
        }
        else // Falling
        {
            if ( BehaviourTools.isLeftRiseSlope( blockBelow ) )
            {
                state = baseVariantSwitch( 
                    new FireAFallToRiseLeftState(),
                    new FireBFallToRiseLeftState(),
                    new FireCFallToRiseLeftState(),
                    new FireDFallToRiseLeftState()
                );
                return;
            }
            if ( BehaviourTools.isRightRiseSlope( blockBelow ) )
            {
                state = baseVariantSwitch(
                    new FireAFallToRiseRightState(),
                    new FireBFallToRiseRightState(),
                    new FireCFallToRiseRightState(),
                    new FireDFallToRiseRightState()
                );
                return;
            }
            state = baseVariantSwitch( 
                new FireAFallingState(),
                new FireBFallingState(),
                new FireCFallingState(),
                new FireDFallingState()
            );
            return;
        }
    }

    private State baseVariantSwitch( State a, State b, State c, State d ) // TODO : State 적용
    {
        if (baseVariant instanceof FireState)
        {
            return ((FireState) baseVariant).baseVariantSwitch( a, b, c, d, state );
        }
        throw new RuntimeException( "Fire not in fire state:" + state );
//        switch ( baseVariant ) // TODO : 주석 삭제
//        {
//        case FIRE_A: // DONE
//            return a;
//        case FIRE_B: // DONE
//            return b;
//        case FIRE_C: // DONE
//            return c;
//        case FIRE_D: // DONE
//            return d;
//        default:
//            throw new RuntimeException( "Fire not in fire state:" + state );
//        }
    }

    @Override
    public void step( World world )
    {
        if (state instanceof FireState)
        {
            ((FireState) state).step(  world, this );
        }
//        switch ( state )  // TODO : 주석 삭제
//        {
//        case FIRE_A_FALLING: // DONE
//        case FIRE_B_FALLING: // DONE
//        case FIRE_C_FALLING: // DONE
//        case FIRE_D_FALLING: // DONE
//        case FIRE_A_FALL_TO_RISE_RIGHT: // DONE
//        case FIRE_B_FALL_TO_RISE_RIGHT: // DONE
//        case FIRE_C_FALL_TO_RISE_RIGHT: // DONE
//        case FIRE_D_FALL_TO_RISE_RIGHT: // DONE
//        case FIRE_A_FALL_TO_RISE_LEFT: // DONE
//        case FIRE_B_FALL_TO_RISE_LEFT: // DONE
//        case FIRE_C_FALL_TO_RISE_LEFT: // DONE
//        case FIRE_D_FALL_TO_RISE_LEFT: // DONE
//            ++y;
//
//            if ( y >= world.size.height )
//            {
//                world.changes.removeFire( this );
//            }
//            return;
//        case FIRE_EXTINGUISHING: // DONE
//            world.changes.removeFire( this );
//            return;
//        default:
//            return;
//        }

    }

    @Override
    public Map<String, String> saveState( boolean runtimeMeta )
    {
        return new HashMap<String, String>();
    }

    @Override
    public void restoreFromState( Map<String, String> state )
    {
    }

    @Override
    public String overlayText()
    {
        return "Fire";
    }
}
