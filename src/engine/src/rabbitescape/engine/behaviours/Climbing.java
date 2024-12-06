package rabbitescape.engine.behaviours;

import static rabbitescape.engine.Token.Type.*;

import java.util.Map;

import rabbitescape.engine.*;
import rabbitescape.engine.state.State;
import rabbitescape.engine.state.rabbit.climbing.*;

public class Climbing extends Behaviour
{
    boolean hasAbility = false;
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

        return !hasAbility && t.pickUpToken( climb, true );
    }

    @Override
    public State newState( BehaviourTools t, boolean triggered )
    {
        return RabbitClimbingCommon.newState( t, triggered, this );
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, State state )
    {
        BehaviourTools t = new BehaviourTools( rabbit, world );

        if( t.rabbitIsClimbing() )
        { // Can't be both on a wall and on a slope.
            rabbit.onSlope = false;
        }

        if ( state instanceof RabbitClimbingCommon )
        {
            return ( ( RabbitClimbingCommon )state ).behave( world, rabbit, this );
        }else
        {
            return false;
        }

    }

    @Override
    public void saveState( Map<String, String> saveState )
    {
        BehaviourState.addToStateIfTrue(
            saveState, "Climbing.hasAbility", hasAbility
        );

        BehaviourState.addToStateIfTrue(
            saveState, "Climbing.abilityActive", abilityActive
        );
    }

    @Override
    public void restoreFromState( Map<String, String> saveState )
    {
        hasAbility = BehaviourState.restoreFromState(
            saveState, "Climbing.hasAbility", hasAbility
        );

        abilityActive = BehaviourState.restoreFromState(
            saveState, "Climbing.abilityActive", abilityActive
        );
    }

    public boolean isAbilityActive()
    {
        return abilityActive;
    }

    public void setAbilityActive( boolean abilityActive )
    {
        this.abilityActive = abilityActive;
    }

    public boolean isHasAbility()
    {
        return hasAbility;
    }

    public void setHasAbility( boolean hasAbility )
    {
        this.hasAbility = hasAbility;
    }
}
