package rabbitescape.engine.state.rabbit.bashing;

import rabbitescape.engine.*;
import rabbitescape.engine.behaviours.Bashing;
import rabbitescape.engine.state.State;
import rabbitescape.engine.state.rabbit.RabbitState;
import rabbitescape.engine.util.Position;

import static rabbitescape.engine.Direction.RIGHT;

public abstract class RabbitBashingCommon implements RabbitState
{

    @Override
    public boolean rabbitIsFalling()
    {
        return false;
    }

    @Override
    public boolean rabbitIsDigging()
    {
        return false;
    }

    @Override
    public boolean rabbitIsClimbing()
    {
        return false;
    }

    @Override
    public boolean rabbitIsBashing()
    {
        return true;
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
    public boolean moveRabbit(
        World world,
        Rabbit rabbit,
        Behaviour behaviour
    )
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

    protected int destX( Rabbit rabbit )
    {
        return ( rabbit.dir == RIGHT ) ? rabbit.x + 1 : rabbit.x - 1;
    }

    static public State newState(
        BehaviourTools t,
        boolean triggered,
        Bashing bashing
    )
    {
        if ( triggered || bashing.getStepsOfBashing() > 0 )
        {
            if (
                t.isOnUpSlope()
                    && t.blockAboveNext() != null
            )
            {
                if (t.blockAboveNext().material == Block.Material.METAL)
                {
                    bashing.setStepsOfBashing( 0 );
                    return t.rl(
                        new RabbitBashingUselesslyRightUpState(),
                        new RabbitBashingUselesslyLeftUpState()
                    );
                }

                else
                {
                    bashing.setStepsOfBashing( 2 );
                    return t.rl(
                        new RabbitBashingUpRightState(),
                        new RabbitBashingUpLeftState()
                    );
                }
            }
            else if (
                t.isOnUpSlope()
                    && t.blockAboveNext() == null
                    && triggered
            )
            {
                return t.rl(
                    new RabbitBashingUselesslyRightUpState(),
                    new RabbitBashingUselesslyLeftUpState()
                );
            }
            else if ( t.blockNext() != null )
            {
                if ( t.blockNext().material == Block.Material.METAL )
                {
                    bashing.setStepsOfBashing( 0 );
                    return t.rl(
                        new RabbitBashingUselesslyRightState(),
                        new RabbitBashingUselesslyLeftState()
                    );
                }
                else
                {
                    bashing.setStepsOfBashing( 2 );
                    return t.rl(
                        new RabbitBashingRightState(),
                        new RabbitBashingLeftState()
                    );
                }
            }
            else if ( triggered )
            {
                return t.rl(
                    new RabbitBashingUselesslyRightState(),
                    new RabbitBashingUselesslyLeftState()
                );
            }
        }
        bashing.setStepsOfBashing( bashing.getStepsOfBashing() - 1 );
        return null;
    }
}
