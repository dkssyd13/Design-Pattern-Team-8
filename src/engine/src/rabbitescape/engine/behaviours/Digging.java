package rabbitescape.engine.behaviours;

import static rabbitescape.engine.ChangeDescription.State.*;
import static rabbitescape.engine.Token.Type.*;

import java.util.Map;

import rabbitescape.engine.*;
import rabbitescape.engine.ChangeDescription.State;

public class Digging extends Behaviour
{
    int stepsOfDigging;

    @Override
    public void cancel()
    {
        stepsOfDigging = 0;
    }

    @Override
    public boolean checkTriggered( Rabbit rabbit, World world )
    {
        BehaviourTools t = new BehaviourTools( rabbit, world );
        return t.pickUpToken( dig );
    }

    @Override
    public State newState( BehaviourTools t, boolean triggered )
    {
        if ( !triggered && stepsOfDigging == 0 )
        {
            return null;
        }

        t.rabbit.possiblyUndoSlopeBashHop( t.world );

        if ( t.rabbit.state == RABBIT_DIGGING )
        {
            stepsOfDigging = 1;
            return RABBIT_DIGGING_2;
        }

        if (
               triggered
            || stepsOfDigging > 0
        )
        {
            if ( t.rabbit.onSlope && t.blockHere() != null )
            {
                stepsOfDigging = 1;
                return RABBIT_DIGGING_ON_SLOPE;
            }
            else if ( t.blockBelow() != null )
            {
                if ( t.blockBelow().material == Block.Material.METAL )
                {
                    stepsOfDigging = 0;
                    return RABBIT_DIGGING_USELESSLY;
                }
                else
                {
                stepsOfDigging = 2;
                return RABBIT_DIGGING;
                }
            }
        }

        --stepsOfDigging;
        return null;
    }

    @Override
    public boolean behave( World world, Rabbit rabbit, State state )
    {
        switch ( state )
        {
            case RABBIT_DIGGING: // DONE
            {
                world.changes.removeBlockAt( rabbit.x, rabbit.y + 1 );
                ++rabbit.y;
                return true;
            }
            case RABBIT_DIGGING_ON_SLOPE: // DONE
            {
                world.changes.removeBlockAt( rabbit.x, rabbit.y );
                rabbit.onSlope = false;
                return true;
            }
            case RABBIT_DIGGING_2: // DONE
            case RABBIT_DIGGING_USELESSLY: // DONE
            {
                return true;
            }
            default:
            {
                return false;
            }
        }
    }

    @Override
    public void saveState( Map<String, String> saveState )
    {
        BehaviourState.addToStateIfGtZero(
            saveState, "Digging.stepsOfDigging", stepsOfDigging );
    }

    @Override
    public void restoreFromState( Map<String, String> saveState )
    {
        stepsOfDigging = BehaviourState.restoreFromState(
            saveState, "Digging.stepsOfDigging", stepsOfDigging );
    }

    public static boolean isDigging( State state )
    {
        switch ( state )
        {
            case RABBIT_DIGGING: // DONE
            case RABBIT_DIGGING_2: // DONE
            case RABBIT_DIGGING_ON_SLOPE: // DONE
            case RABBIT_DIGGING_USELESSLY: // DONE
                return true;
            default:
                return false;
        }
    }

}