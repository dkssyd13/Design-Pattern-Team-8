package rabbitescape.engine.state.fire;

import rabbitescape.engine.*;
import rabbitescape.engine.state.State;
import rabbitescape.engine.textworld.Chars;


public abstract class FireStateCommon implements State
{
    public void step( World world, Fire fire ){

    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {

    }

    static private State baseVariantSwitch(
        State a,
        State b,
        State c,
        State d,
        Fire fire
    ){
        if (fire.getBaseVariant() instanceof FireStateCommon )
        {
            return (( FireStateCommon ) fire.getBaseVariant()).baseVariantSwitch( a, b, c, d, fire.state );
        }
        throw new RuntimeException( "Fire not in fire state:" + fire.state );
    }


    public State baseVariantSwitch( State a, State b, State c, State d, State currentState )
    {
        throw new RuntimeException( "Fire not in fire state:" + currentState );
    }

    public static State stateForVariant( int variant ){
        switch ( variant )
        {
            case 0:
                return new FireAState();
            case 1:
                return new FireBState();
            case 2:
                return new FireCState();
            case 3:
                return new FireDState();
        }
        throw new RuntimeException(
            "Variant outside expected range (0 - 3):" + variant );
    }

    static public void calcNewState( World world, Fire fire )
    {
        // Check if being extinguished.
        for ( WaterRegion waterRegion : world.waterTable.getItemsAt( fire.x, fire.y ) )
        {
            if ( waterRegion.getContents() > 0 )
            {
                fire.state = new FireExtinguishingState();
                return;
            }
        }

        Block blockBelow = world.getBlockAt( fire.x, fire.y + 1 );
        // Note: when flatBelow is true may be on a slope with a flat below,
        // or sitting on the flat
        boolean flatBelow = BehaviourTools.s_isFlat( blockBelow );
        boolean still = (
            flatBelow
                || ( world.getBlockAt( fire.x, fire.y ) != null )
                || BridgeTools.someoneIsBridgingAt( world, fire.x, fire.y )
        );
        if ( still )
        {
            Block onBlock = world.getBlockAt( fire.x, fire.y );
            if ( BehaviourTools.isLeftRiseSlope( onBlock ) )
            {
                fire.state = baseVariantSwitch(
                    new FireARiseLeftState(),
                    new FireBRiseLeftState(),
                    new FireCRiseLeftState(),
                    new FireDRiseLeftState(),
                    fire
                );
                return;
            }
            if ( BehaviourTools.isRightRiseSlope( onBlock ) )
            {
                fire.state = baseVariantSwitch(
                    new FireARiseRightState(),
                    new FireBRiseRightState(),
                    new FireCRiseRightState(),
                    new FireDRiseRightState(),
                    fire
                );
                return;
            }
            // TODO: check here for fire falling on a bridger.
            // Fire going to a falling state may be OK
            // as bridger is burnt
            if ( flatBelow )
            {
                fire.state = fire.getBaseVariant();
                return;
            }
        }
        else // Falling
        {
            if ( BehaviourTools.isLeftRiseSlope( blockBelow ) )
            {
                fire.state = baseVariantSwitch(
                    new FireAFallToRiseLeftState(),
                    new FireBFallToRiseLeftState(),
                    new FireCFallToRiseLeftState(),
                    new FireDFallToRiseLeftState(),
                    fire
                );
                return;
            }
            if ( BehaviourTools.isRightRiseSlope( blockBelow ) )
            {
                fire.state = baseVariantSwitch(
                    new FireAFallToRiseRightState(),
                    new FireBFallToRiseRightState(),
                    new FireCFallToRiseRightState(),
                    new FireDFallToRiseRightState(),
                    fire
                );
                return;
            }
            fire.state = baseVariantSwitch(
                new FireAFallingState(),
                new FireBFallingState(),
                new FireCFallingState(),
                new FireDFallingState(),
                fire
            );
            return;
        }
    }
}
