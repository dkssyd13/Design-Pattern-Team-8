package rabbitescape.engine.behaviours;


import static rabbitescape.engine.Token.Type.*;

import java.util.Map;

import rabbitescape.engine.*;
import rabbitescape.engine.state.State;
import rabbitescape.engine.state.rabbit.digging.RabbitDiggingCommon;


public class Digging extends Behaviour
{
    int stepsOfDigging;

    @Override
    public void cancel()
    {
        stepsOfDigging = 0;
    }

    @Override
    public boolean checkTriggered( Rabbit rabbit, World world )
    {
        BehaviourTools t = new BehaviourTools( rabbit, world );
        return t.pickUpToken( dig );
    }

    @Override
    public State newState( BehaviourTools t, boolean triggered )
    {
        return RabbitDiggingCommon.newState(t, triggered, this);
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, State state )
    {
        if ( state instanceof RabbitDiggingCommon )
        {
            return ( ( RabbitDiggingCommon )state ).behave(
                world,
                rabbit,
                this
            );
        }else
        {
            return false;
        }
    }

    @Override
    public void saveState( Map<String, String> saveState )
    {
        BehaviourState.addToStateIfGtZero(
            saveState, "Digging.stepsOfDigging", stepsOfDigging );
    }

    @Override
    public void restoreFromState( Map<String, String> saveState )
    {
        stepsOfDigging = BehaviourState.restoreFromState(
            saveState, "Digging.stepsOfDigging", stepsOfDigging );
    }

    public static boolean isDigging( State state )
    {
        if (state instanceof RabbitDiggingCommon)
        {
            return ( ( RabbitDiggingCommon )state ).isDigging();
        }else
        {
            return false;
        }
    }

    public int getStepsOfDigging()
    {
        return stepsOfDigging;
    }

    public void setStepsOfDigging( int stepsOfDigging )
    {
        this.stepsOfDigging = stepsOfDigging;
    }
}
