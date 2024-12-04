package rabbitescape.engine.state.rabbit;

import rabbitescape.engine.Behaviour;
import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.World;
import rabbitescape.engine.config.TapTimer;
import rabbitescape.engine.textworld.Chars;
import rabbitescape.engine.util.Position;

public class RabbitOutOfBoundsState implements RabbitState
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
        checkMars( world, rabbit );
        world.changes.killRabbit( rabbit );
        return true;
    }

    /**
     * Test if mars mode has been triggered
     */
    private void checkMars( World world, Rabbit rabbit)
    {
        /* The rabbit must leave the world at the correct coordinates,
         * the index count is likely to only be correct if this is the
         * first rabbit out of the entrance, and it must be the correct
         * level.
         */
        if ( 12 == rabbit.x && -1 == rabbit.y &&
            world.getRabbitIndexCount() == 2 &&
            world.name.equals( "Ghost versus pie" ) )
        {
            TapTimer.setMars();
        }
    }

    @Override
    public Position whereBridging( int x, int y )
    {
        return null;
    }

    @Override
    public char bridgingStage( ChangeDescription.State state )
    {
        return ' ';
    }

    @Override
    public String name()
    {
        return "RABBIT_OUT_OF_BOUNDS";
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {

    }
}
