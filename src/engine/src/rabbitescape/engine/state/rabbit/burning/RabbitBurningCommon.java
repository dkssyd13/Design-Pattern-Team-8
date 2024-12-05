package rabbitescape.engine.state.rabbit.burning;

import rabbitescape.engine.*;
import rabbitescape.engine.state.State;
import rabbitescape.engine.state.rabbit.RabbitState;
import rabbitescape.engine.textworld.Chars;
import rabbitescape.engine.util.Position;

public abstract class RabbitBurningCommon implements RabbitState
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
        return false;
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
        world.changes.killRabbit( rabbit );
        return true;
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
        chars.set(  change.x, change.y, 'X' );
    }

    public static State newState(
        BehaviourTools t, boolean triggered
    ){
        if ( triggered )
        {
            if ( t.rabbit.onSlope )
            {
                return new RabbitBurningOnSlopeState();
            }
            else
            {
                return new RabbitBurningState();
            }
        }

        return null;
    }
}
