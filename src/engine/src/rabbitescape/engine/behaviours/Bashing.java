package rabbitescape.engine.behaviours;


import static rabbitescape.engine.Direction.*;
import static rabbitescape.engine.Token.Type.*;

import java.util.Map;

import rabbitescape.engine.*;
import rabbitescape.engine.state.State;
import rabbitescape.engine.state.rabbit.RabbitState;
import rabbitescape.engine.state.rabbit.bashing.RabbitBashingCommon;

// TODO : 주석 삭제
public class Bashing extends Behaviour
{
    private int stepsOfBashing;

    @Override
    public void cancel()
    {
        stepsOfBashing = 0;
    }

    @Override
    public boolean checkTriggered( Rabbit rabbit, World world )
    {
        BehaviourTools t = new BehaviourTools( rabbit, world );

        return t.pickUpToken( bash );
    }

    @Override
    public State newState( BehaviourTools t, boolean triggered )
    {
        return RabbitBashingCommon.newState( t, triggered, this );
//        if ( triggered || stepsOfBashing > 0 )
//        {
//            if (
//                   t.isOnUpSlope()
//                && t.blockAboveNext() != null
//            )
//            {
//                if (t.blockAboveNext().material == Block.Material.METAL)
//                {
//                    stepsOfBashing = 0;
//                    return t.rl(
//                        RABBIT_BASHING_USELESSLY_RIGHT_UP,
//                        RABBIT_BASHING_USELESSLY_LEFT_UP
//                        );
//                }
//                else
//                {
//                    stepsOfBashing = 2;
//                    return t.rl(
//                        RABBIT_BASHING_UP_RIGHT,
//                        RABBIT_BASHING_UP_LEFT
//                    );
//                }
//            }
//            else if (
//                t.isOnUpSlope()
//             && t.blockAboveNext() == null
//             && triggered
//            )
//            {
//                return t.rl(
//                    RABBIT_BASHING_USELESSLY_RIGHT_UP,
//                    RABBIT_BASHING_USELESSLY_LEFT_UP
//                );
//            }
//            else if ( t.blockNext() != null )
//            {
//                if ( t.blockNext().material == Block.Material.METAL )
//                {
//                    stepsOfBashing = 0;
//                    return t.rl(
//                        RABBIT_BASHING_USELESSLY_RIGHT,
//                        RABBIT_BASHING_USELESSLY_LEFT
//                    );
//                }
//                else
//                {
//                    stepsOfBashing = 2;
//                    return t.rl(
//                        RABBIT_BASHING_RIGHT,
//                        RABBIT_BASHING_LEFT
//                    );
//                }
//            }
//            else if ( triggered )
//            {
//                return t.rl(
//                    RABBIT_BASHING_USELESSLY_RIGHT,
//                    RABBIT_BASHING_USELESSLY_LEFT
//                );
//            }
//        }
//        --stepsOfBashing;
//        return null;
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, State state )
    {
        if ( state instanceof RabbitBashingCommon )
        {
            return ((RabbitBashingCommon) state).behave( world, rabbit, this );
        }
        rabbit.slopeBashHop = false;
        return false;
//        switch ( state )
//        {
//            case RABBIT_BASHING_RIGHT: // DONE
//            case RABBIT_BASHING_LEFT: // DONE
//            {
//                rabbit.slopeBashHop = false;
//                world.changes.removeBlockAt( destX( rabbit ), rabbit.y );
//                return true;
//            }
//            case RABBIT_BASHING_UP_RIGHT: // DONE
//            case RABBIT_BASHING_UP_LEFT: // DONE
//            {
//                world.changes.removeBlockAt( destX( rabbit ), rabbit.y - 1 );
//                rabbit.slopeBashHop = true;
//                rabbit.y -= 1;
//                return true;
//            }
//            case RABBIT_BASHING_USELESSLY_RIGHT: // DONE
//            case RABBIT_BASHING_USELESSLY_LEFT: // DONE
//            {
//                rabbit.slopeBashHop = false;
//                return true;
//            }
//            case RABBIT_BASHING_USELESSLY_RIGHT_UP: // DONE
//            case RABBIT_BASHING_USELESSLY_LEFT_UP: // DONE
//            {
//                rabbit.slopeBashHop = true;
//                rabbit.y -= 1;
//                return true;
//            }
//            default:
//            {
//                rabbit.slopeBashHop = false;
//                return false;
//            }
//        }
    }

    private int destX( Rabbit rabbit )
    {
        return ( rabbit.dir == RIGHT ) ? rabbit.x + 1 : rabbit.x - 1;
    }

    @Override
    public void saveState( Map<String, String> saveState )
    {
        BehaviourState.addToStateIfGtZero(
            saveState, "Bashing.stepsOfBashing", stepsOfBashing
        );
    }

    @Override
    public void restoreFromState( Map<String, String> saveState )
    {
        stepsOfBashing = BehaviourState.restoreFromState(
            saveState, "Bashing.stepsOfBashing", stepsOfBashing
        );

        if ( stepsOfBashing > 0 )
        {
            ++stepsOfBashing;
        }
    }

    public int getStepsOfBashing()
    {
        return stepsOfBashing;
    }

    public void setStepsOfBashing( int stepsOfBashing )
    {
        this.stepsOfBashing = stepsOfBashing;
    }
}
