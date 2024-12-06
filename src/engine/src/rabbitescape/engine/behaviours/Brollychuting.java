package rabbitescape.engine.behaviours;

import static rabbitescape.engine.Token.Type.brolly;

import java.util.Map;

import rabbitescape.engine.Behaviour;
import rabbitescape.engine.BehaviourState;
import rabbitescape.engine.BehaviourTools;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.World;
import rabbitescape.engine.state.State;
import rabbitescape.engine.state.rabbit.RabbitBrollychutingState;

public class Brollychuting extends Behaviour
{
    boolean hasAbility = false;
    private final Climbing climbing;
    private final Digging digging;

    public Brollychuting( Climbing climbing, Digging digging )
    {
        this.climbing = climbing;
        this.digging = digging;
    }

    @Override
    public State newState( BehaviourTools t, boolean triggered )
    {
        return RabbitBrollychutingState.newState( t, triggered, this);
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, State state )
    {
        if ( state instanceof RabbitBrollychutingState )
        {
            return ((RabbitBrollychutingState)state).behave( world, rabbit, this );
        }
        return false;
    }

    public boolean hasBrolly()
    {
        return hasAbility;
    }

    @Override
    public boolean checkTriggered( Rabbit rabbit, World world )
    {
        BehaviourTools t = new BehaviourTools( rabbit, world );

        if ( !hasAbility && t.pickUpToken( brolly, true ) )
        {
            return true;
        }

        if( !hasAbility )
        {
            return false;
        }

        if ( climbing.abilityActive || digging.stepsOfDigging > 2 )
        {
            return false;
        }

        if ( t.isFlat( t.blockBelow() ) )
        {
            return false;
        }

        if (
               rabbit.onSlope
            && !t.blockHereJustRemoved()
        )
        {
            return false;
        }

        return true;
    }

    @Override
    public void cancel()
    {
    }

    @Override
    public void saveState( Map<String, String> saveState )
    {
        BehaviourState.addToStateIfTrue(
            saveState, "Brollychuting.hasAbility", hasAbility
        );

    }

    @Override
    public void restoreFromState( Map<String, String> saveState )
    {
        hasAbility = BehaviourState.restoreFromState(
            saveState, "Brollychuting.hasAbility", hasAbility
        );

    }

    public boolean isHasAbility()
    {
        return hasAbility;
    }

    public void setHasAbility( boolean hasAbility )
    {
        this.hasAbility = hasAbility;
    }

    public Climbing getClimbing()
    {
        return climbing;
    }

    public Digging getDigging()
    {
        return digging;
    }
}
