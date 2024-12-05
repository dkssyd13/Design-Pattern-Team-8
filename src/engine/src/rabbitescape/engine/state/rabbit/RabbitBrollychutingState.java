package rabbitescape.engine.state.rabbit;

import rabbitescape.engine.*;
import rabbitescape.engine.behaviours.Brollychuting;
import rabbitescape.engine.state.State;
import rabbitescape.engine.state.rabbit.falling.RabbitFalling1OntoLowerLeftState;
import rabbitescape.engine.state.rabbit.falling.RabbitFalling1OntoLowerRightState;
import rabbitescape.engine.state.rabbit.falling.RabbitFalling1OntoRiseLeftState;
import rabbitescape.engine.state.rabbit.falling.RabbitFalling1OntoRiseRightState;
import rabbitescape.engine.textworld.Chars;
import rabbitescape.engine.util.Position;

public class RabbitBrollychutingState implements RabbitState
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
        rabbit.y = rabbit.y + 1;
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
    public String name()
    {
        return "RABBIT_BROLLYCHUTING";
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x, change.y + 1, ':' );
    }

    static public State newState(
        BehaviourTools t,
        boolean triggered,
        Brollychuting brollychuting
    )
    {
        if ( triggered )
        {
//            hasAbility = true;
            brollychuting.setHasAbility( true );
        }

//        if( !hasAbility )
        if( !brollychuting.isHasAbility() )
        {
            return null;
        }

//        if ( climbing.abilityActive )
        if ( brollychuting.getClimbing().abilityActive )
        {
            return null;
        }

        Block below = t.blockBelow();

        if ( t.isFlat( below ) )
        {
            return null;
        }

        if (
            t.rabbit.onSlope
                && !t.blockHereJustRemoved()
        )
        {
            return null;
        }

        if ( below != null )
        {
            if ( t.isUpSlope( below ) )
            {
                return t.rl(
//                    RABBIT_FALLING_1_ONTO_RISE_RIGHT,
//                    RABBIT_FALLING_1_ONTO_RISE_LEFT
                    new RabbitFalling1OntoRiseRightState(),
                    new RabbitFalling1OntoRiseLeftState()
                );
            }
            else // Must be a slope in the opposite direction
            {
                return t.rl(
//                    RABBIT_FALLING_1_ONTO_LOWER_RIGHT,
//                    RABBIT_FALLING_1_ONTO_LOWER_LEFT
                    new RabbitFalling1OntoLowerRightState(),
                    new RabbitFalling1OntoLowerLeftState()
                );
            }
        }

//        return RABBIT_BROLLYCHUTING;
        return new RabbitBrollychutingState();
    }
}
