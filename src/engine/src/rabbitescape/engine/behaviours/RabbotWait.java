package rabbitescape.engine.behaviours;

import rabbitescape.engine.Behaviour;
import rabbitescape.engine.BehaviourTools;

import rabbitescape.engine.Direction;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.World;
import rabbitescape.engine.state.State;
import rabbitescape.engine.state.rabbit.RabbitState;
import rabbitescape.engine.state.rabbit.waiting.RabbitWaitingCommon;
import rabbitescape.engine.state.rabbit.waiting.RabbitWaitingLeftState;
import rabbitescape.engine.state.rabbit.waiting.RabbitWaitingRightState;

public class RabbotWait extends Behaviour
{
    private boolean within1Vertically( Rabbit otherRabbit, Rabbit rabbit )
    {
        return ( Math.abs( otherRabbit.y - rabbit.y ) < 2 );
    }

    private boolean noseToNose( Rabbit otherRabbit, Rabbit rabbit )
    {
        if ( 
            otherRabbit.x == rabbit.x - 1 &&
            otherRabbit.dir == Direction.RIGHT &&
            rabbit.dir == Direction.LEFT 
        )
        {
            return true;
        }
        else if ( 
            otherRabbit.x == rabbit.x + 1 &&
            otherRabbit.dir == Direction.LEFT &&
            rabbit.dir == Direction.RIGHT 
        )
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void cancel()
    {
    }

    @Override
    public boolean checkTriggered( Rabbit rabbit, World world )
    {
        if (
            rabbit.type == Rabbit.Type.RABBOT &&
            !Blocking.isBlocking(rabbit.state) &&
            !Digging.isDigging(rabbit.state)
        )
        {
            for ( Rabbit otherRabbit : world.rabbits )
            {
                if (
                    otherRabbit.type == Rabbit.Type.RABBIT &&
                    within1Vertically( otherRabbit, rabbit ) &&
                    noseToNose( otherRabbit, rabbit ) &&
                    !Blocking.isBlocking(otherRabbit.state)
                )
                {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public State newState( BehaviourTools t, boolean triggered )
    {
        if ( triggered )
        {
            return t.rl( 
//                State.RABBIT_WAITING_RIGHT,
//                State.RABBIT_WAITING_LEFT
                new RabbitWaitingRightState(),
                new RabbitWaitingLeftState()
            );
        }
        else
        {
            return null;
        }
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, State state )
    {
        if ( state instanceof RabbitWaitingCommon )
        {
            return ((RabbitWaitingCommon) state).behave( world, rabbit, this );
        }else
        {
            return false;
        }
//        if ( // TODO : 주석 삭제
//            state == State.RABBIT_WAITING_LEFT || // DONE
//            state == State.RABBIT_WAITING_RIGHT  // DONE
//        )
//        {
//            return true;
//        }
//
//        return false;
    }
}
