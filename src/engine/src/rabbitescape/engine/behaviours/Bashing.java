package rabbitescape.engine.behaviours;


import static rabbitescape.engine.Token.Type.*;

import java.util.Map;

import rabbitescape.engine.*;
import rabbitescape.engine.state.State;
import rabbitescape.engine.state.rabbit.bashing.RabbitBashingCommon;

public class Bashing extends Behaviour
{
    private int stepsOfBashing;

    @Override
    public void cancel()
    {
        stepsOfBashing = 0;
    }

    @Override
    public boolean checkTriggered( Rabbit rabbit, World world )
    {
        BehaviourTools t = new BehaviourTools( rabbit, world );

        return t.pickUpToken( bash );
    }

    @Override
    public State newState( BehaviourTools t, boolean triggered )
    {
        return RabbitBashingCommon.newState( t, triggered, this );
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, State state )
    {
        if ( state instanceof RabbitBashingCommon )
        {
            return ((RabbitBashingCommon) state).behave( world, rabbit, this );
        }
        rabbit.slopeBashHop = false;
        return false;
    }

    @Override
    public void saveState( Map<String, String> saveState )
    {
        BehaviourState.addToStateIfGtZero(
            saveState, "Bashing.stepsOfBashing", stepsOfBashing
        );
    }

    @Override
    public void restoreFromState( Map<String, String> saveState )
    {
        stepsOfBashing = BehaviourState.restoreFromState(
            saveState, "Bashing.stepsOfBashing", stepsOfBashing
        );

        if ( stepsOfBashing > 0 )
        {
            ++stepsOfBashing;
        }
    }

    public int getStepsOfBashing()
    {
        return stepsOfBashing;
    }

    public void setStepsOfBashing( int stepsOfBashing )
    {
        this.stepsOfBashing = stepsOfBashing;
    }
}
