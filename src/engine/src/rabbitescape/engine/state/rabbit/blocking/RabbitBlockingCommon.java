package rabbitescape.engine.state.rabbit.blocking;

import rabbitescape.engine.*;
import rabbitescape.engine.behaviours.Blocking;
import rabbitescape.engine.state.State;
import rabbitescape.engine.state.rabbit.RabbitState;
import rabbitescape.engine.textworld.Chars;
import rabbitescape.engine.util.Position;

public abstract class RabbitBlockingCommon implements RabbitState
{
    @Override
    public boolean rabbitIsFalling()
    {
        return false;
    }

    @Override
    public boolean rabbitIsClimbing()
    {
        return false;
    }

    @Override
    public boolean rabbitIsDigging()
    {
        return false;
    }

    @Override
    public boolean rabbitIsBashing()
    {
        return false;
    }

    @Override
    public boolean isBlocking()
    {
        return true;
    }

    @Override
    public boolean isDigging()
    {
        return false;
    }

    @Override
    public boolean moveRabbit( World world, Rabbit rabbit, Behaviour behaviour )
    {
        return false;
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, Behaviour behaviour )
    {
        return false;
    }

    @Override
    public Position whereBridging( int x, int y )
    {
        return null;
    }

    @Override
    public char bridgingStage( State state )
    {
        return ' ';
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x, change.y, 'H' );

    }

    static public State newState(
        BehaviourTools t,
        boolean triggered,
        Blocking blocking
    )
    {
        if ( blocking.isAbilityActive() || triggered )
        {
            t.rabbit.possiblyUndoSlopeBashHop( t.world );
            blocking.setAbilityActive( true );
            Block here = t.blockHere();
            if( BehaviourTools.isRightRiseSlope( here ) )
            {
                return new RabbitBlockingRiseRightState();
            }
            else if ( BehaviourTools.isLeftRiseSlope( here ) )
            {
                return new RabbitBlockingRiseLeftState();
            }
            else
            {
                return new RabbitBlockingState();
            }
        }

        return null;
    }
}
