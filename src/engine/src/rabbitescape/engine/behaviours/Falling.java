package rabbitescape.engine.behaviours;

import static rabbitescape.engine.Block.Shape.*;

import java.util.Map;

import rabbitescape.engine.*;
import rabbitescape.engine.state.State;
import rabbitescape.engine.state.rabbit.digging.RabbitDiggingState;
import rabbitescape.engine.state.rabbit.falling.*;

public class Falling extends Behaviour
{
    private int heightFallen = 0;

    private final Climbing climbing;
    private final Brollychuting brollychuting;
    private final int fatalHeight;

    public Falling( 
        Climbing climbing, 
        Brollychuting brollychuting,
        int fatalHeight 
    )
    {
        this.climbing = climbing;
        this.brollychuting = brollychuting;
        this.fatalHeight = fatalHeight;
    }

    public boolean isFallingToDeath()
    {
        return heightFallen > fatalHeight ;
    }

    @Override
    public void cancel()
    {
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, State state )
    {
        if (state instanceof RabbitFallingCommon)
        {
            return ((RabbitFallingCommon) state).behave(world, rabbit, this);
        }else
        {
            heightFallen = 0;
            return false;
        }
    }

    @Override
    public boolean checkTriggered( Rabbit rabbit, World world )
    {
        if (   climbing.abilityActive
            || rabbit.state instanceof RabbitDiggingState
            || brollychuting.hasBrolly() )
        {
            return false;
        }

        BehaviourTools t = new BehaviourTools( rabbit, world );

        //noinspection RedundantIfStatement
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
    public State newState( BehaviourTools t, boolean triggered )
    {
        return RabbitFallingCommon.newState( t, triggered, this );
    }

    @Override
    public void saveState( Map<String, String> saveState )
    {
        BehaviourState.addToStateIfGtZero(
            saveState, "Falling.heightFallen", heightFallen
        );
    }

    @Override
    public void restoreFromState( Map<String, String> saveState )
    {
        heightFallen = BehaviourState.restoreFromState(
            saveState, "Falling.heightFallen", heightFallen
        );
    }

    public int getHeightFallen()
    {
        return heightFallen;
    }

    public void setHeightFallen( int heightFallen )
    {
        this.heightFallen = heightFallen;
    }

    public Climbing getClimbing()
    {
        return climbing;
    }

    public Brollychuting getBrollychuting()
    {
        return brollychuting;
    }

    public int getFatalHeight()
    {
        return fatalHeight;
    }
}
