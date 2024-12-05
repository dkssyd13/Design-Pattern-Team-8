package rabbitescape.engine.behaviours;

import static rabbitescape.engine.Token.Type.*;

import java.util.Map;

import rabbitescape.engine.*;
import rabbitescape.engine.state.State;
import rabbitescape.engine.state.rabbit.blocking.RabbitBlockingCommon;
import rabbitescape.engine.state.rabbit.blocking.RabbitBlockingRiseLeftState;
import rabbitescape.engine.state.rabbit.blocking.RabbitBlockingRiseRightState;
import rabbitescape.engine.state.rabbit.blocking.RabbitBlockingState;

// TODO : 주석 삭제
public class Blocking extends Behaviour
{
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
        return t.pickUpToken( block );
    }

    @Override
    public State newState( BehaviourTools t, boolean triggered )
    {
        if ( abilityActive || triggered )
        {
            t.rabbit.possiblyUndoSlopeBashHop( t.world );
            abilityActive = true;
            Block here = t.blockHere();
            if( BehaviourTools.isRightRiseSlope( here ) )
            {
//                return RABBIT_BLOCKING_RISE_RIGHT;
                return new RabbitBlockingRiseRightState();
            }
            else if ( BehaviourTools.isLeftRiseSlope( here ) )
            {
//                return RABBIT_BLOCKING_RISE_LEFT;
                return new RabbitBlockingRiseLeftState();
            }
            else
            {
//                return RABBIT_BLOCKING;
                return new RabbitBlockingState();
            }
        }

        return null;
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, State state )
    {
        return isBlocking( state );
    }

    @Override
    public void saveState( Map<String, String> saveState )
    {
        BehaviourState.addToStateIfTrue(
            saveState, "Blocking.abilityActive", abilityActive
        );
    }

    @Override
    public void restoreFromState( Map<String, String> saveState )
    {
        abilityActive = BehaviourState.restoreFromState(
            saveState, "Blocking.abilityActive", abilityActive
        );
    }

    public static boolean blockerAt( World world, int nextX, int nextY )
    {
        Rabbit[] rabbits = world.getRabbitsAt( nextX, nextY );
        for ( Rabbit r : rabbits )
        {
            if ( isBlocking( r.state ) )
            {
                return true;
            }
        }
        return false;
    }

    static boolean isBlocking( State s )
    {
        if ( s instanceof RabbitBlockingCommon )
        {
            return ( ( RabbitBlockingCommon )s ).isBlocking();
        }
        return false;
//        switch ( s ) {
//        case RABBIT_BLOCKING: // DONE
//        case RABBIT_BLOCKING_RISE_RIGHT: // DONE
//        case RABBIT_BLOCKING_RISE_LEFT: // DONE
//            return true;
//        default:
//            return false;
//        }
    }
}
