package rabbitescape.engine.behaviours;

import static rabbitescape.engine.Token.Type.*;

import java.util.Map;

import rabbitescape.engine.*;
import rabbitescape.engine.state.State;
import rabbitescape.engine.state.rabbit.blocking.RabbitBlockingCommon;

public class Blocking extends Behaviour
{
    public boolean abilityActive = false;

    @Override
    public void cancel()
    {
        abilityActive = false;
    }

    @Override
    public boolean checkTriggered( Rabbit rabbit, World world )
    {
        BehaviourTools t = new BehaviourTools( rabbit, world );
        return t.pickUpToken( block );
    }

    @Override
    public State newState( BehaviourTools t, boolean triggered )
    {
        return RabbitBlockingCommon.newState( t, triggered, this );
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, State state )
    {
        return isBlocking( state );
    }

    @Override
    public void saveState( Map<String, String> saveState )
    {
        BehaviourState.addToStateIfTrue(
            saveState, "Blocking.abilityActive", abilityActive
        );
    }

    @Override
    public void restoreFromState( Map<String, String> saveState )
    {
        abilityActive = BehaviourState.restoreFromState(
            saveState, "Blocking.abilityActive", abilityActive
        );
    }

    public static boolean blockerAt( World world, int nextX, int nextY )
    {
        Rabbit[] rabbits = world.getRabbitsAt( nextX, nextY );
        for ( Rabbit r : rabbits )
        {
            if ( isBlocking( r.state ) )
            {
                return true;
            }
        }
        return false;
    }

    static boolean isBlocking( State s )
    {
        if ( s instanceof RabbitBlockingCommon )
        {
            return ( ( RabbitBlockingCommon )s ).isBlocking();
        }
        return false;
    }

    public boolean isAbilityActive()
    {
        return abilityActive;
    }

    public void setAbilityActive( boolean abilityActive )
    {
        this.abilityActive = abilityActive;
    }
}
