package rabbitescape.engine.behaviours;

import static rabbitescape.engine.Token.Type.*;

import java.util.Map;

import rabbitescape.engine.*;
import rabbitescape.engine.state.State;
import rabbitescape.engine.state.rabbit.bridging.*;

public class Bridging extends Behaviour
{
    public enum BridgeType
    {
        ALONG,
        UP,
        DOWN_UP
    }

    private int smallSteps = 0;
    private int bigSteps = 0;
    private BridgeType bridgeType = BridgeType.ALONG;

    @Override
    public void cancel()
    {
        bigSteps = 0;
        smallSteps = 0;
    }

    @Override
    public boolean checkTriggered( Rabbit rabbit, World world )
    {
        nextStep();

        if ( bigSteps <= 0 )
            // Only pick up a token if we've finished, and we can bridge
        {
            BehaviourTools t = new BehaviourTools( rabbit, world );

            State possibleState = bridgingState( t, 3, 3, bridgeType );

            if ( possibleState != null ) // Only pick up if we can bridge
            {
                return t.pickUpToken( bridge );
            }
        }
        return false;
    }

    @Override
    public State newState( BehaviourTools t, boolean triggered )
    {
        if ( triggered )
        {
            smallSteps = 3;
            bigSteps = 3;
        }

        State ret = bridgingState( t, bigSteps, smallSteps, bridgeType );

        if ( ret == null )
        {
            bigSteps = 0;
        }

        if ( bigSteps <= 0 )
        {
            smallSteps = 0;
            return null;   // Finished bridging
        }

        return ret;
    }

    private static State bridgingState(
        BehaviourTools t,
        int bs,
        int ss,
        BridgeType bt
    )
    {
        return RabbitBridgingCommon.bridgingState( t, bs, ss, bt );
    }

    private void nextStep()
    {
        --smallSteps;
        if ( smallSteps <= 0 )
        {
            --bigSteps;
            smallSteps = 3;
        }
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, State state )
    {
        boolean handled = moveRabbit( world, rabbit, state );

        if ( handled )
        {
            // If we're bridging, we're on a slope
            rabbit.onSlope = true;
        }

        return handled;
    }

    private boolean moveRabbit( World world, Rabbit rabbit, State state )
    {
        if ( state instanceof RabbitBridgingCommon)
        {
            return ( (RabbitBridgingCommon) state ).moveRabbit( world, rabbit, this );
        }
        return false;
    }

    @Override
    public void saveState( Map<String, String> saveState )
    {
        BehaviourState.addToStateIfNotDefault(
            saveState,
            "Bridging.bridgeType",
            bridgeType.toString(),
            BridgeType.ALONG.toString()
        );

        BehaviourState.addToStateIfGtZero(
            saveState, "Bridging.bigSteps", bigSteps
        );

        BehaviourState.addToStateIfGtZero(
            saveState, "Bridging.smallSteps", smallSteps
        );
    }

    @Override
    public void restoreFromState( Map<String, String> saveState )
    {
        bridgeType = BridgeType.valueOf(
            BehaviourState.restoreFromState(
                saveState, "Bridging.bridgeType", bridgeType.toString()
            )
        );

        bigSteps = BehaviourState.restoreFromState(
            saveState, "Bridging.bigSteps", bigSteps
        );

        smallSteps = BehaviourState.restoreFromState(
            saveState, "Bridging.smallSteps", smallSteps
        );

        if ( smallSteps > 0 )
        {
            ++smallSteps;
        }
    }

    public BridgeType getBridgeType()
    {
        return bridgeType;
    }

    public void setBridgeType( BridgeType bridgeType )
    {
        this.bridgeType = bridgeType;
    }
}
