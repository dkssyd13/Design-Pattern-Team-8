package rabbitescape.engine.behaviours;

import rabbitescape.engine.Behaviour;
import rabbitescape.engine.BehaviourTools;

import rabbitescape.engine.Rabbit;
import rabbitescape.engine.World;
import rabbitescape.engine.state.State;
import rabbitescape.engine.state.rabbit.burning.RabbitBurningCommon;

// TODO : 주석 삭제
public class Burning extends Behaviour
{
    @Override
    public void cancel()
    {
    }

    @Override
    public boolean checkTriggered( Rabbit rabbit, World world )
    {
        return world.fireAt( rabbit.x, rabbit.y );
    }

    @Override
    public State newState(
        BehaviourTools t, boolean triggered
        )
    {
        return RabbitBurningCommon.newState( t, triggered );
//        if ( triggered )
//        {
//            if ( t.rabbit.onSlope )
//            {
//                return RABBIT_BURNING_ON_SLOPE;
//            }
//            else
//            {
//                return RABBIT_BURNING;
//            }
//        }
//
//        return null;
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, State state )
    {
        if ( state instanceof RabbitBurningCommon )
        {
            return ( ( RabbitBurningCommon )state ).behave(
                world,
                rabbit,
                this
            );
        } else
        {
            return false;
        }
//        switch ( state )
//        {
//        case RABBIT_BURNING:
//        case RABBIT_BURNING_ON_SLOPE:
//        {
//            world.changes.killRabbit( rabbit );
//            return true;
//        }
//        default:
//        {
//            return false;
//        }
//        }
    }
}
