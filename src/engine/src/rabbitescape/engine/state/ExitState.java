package rabbitescape.engine.state;

import rabbitescape.engine.BehaviourTools;
import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.behaviours.Exiting;
import rabbitescape.engine.state.rabbit.climbing.RabbitClimbingLeftContinue2State;
import rabbitescape.engine.state.rabbit.climbing.RabbitClimbingRightContinue2State;
import rabbitescape.engine.state.rabbit.entering.RabbitEnteringExitClimbingLeftState;
import rabbitescape.engine.state.rabbit.entering.RabbitEnteringExitClimbingRightState;
import rabbitescape.engine.state.rabbit.entering.RabbitEnteringExitState;
import rabbitescape.engine.textworld.Chars;

public class ExitState implements State{
    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {

    }

    @Override
    public String name()
    {
        return "EXIT";
    }

    static public State newState( BehaviourTools t, boolean triggered, Exiting exiting )
    {
        if ( triggered )
        {
//            if ( t.rabbit.state == RABBIT_CLIMBING_LEFT_CONTINUE_2 ) // DONE
            if ( t.rabbit.state instanceof RabbitClimbingLeftContinue2State ) // DONE
            {
//                return RABBIT_ENTERING_EXIT_CLIMBING_LEFT;
                return new RabbitEnteringExitClimbingLeftState();
            }
//            if ( t.rabbit.state == RABBIT_CLIMBING_RIGHT_CONTINUE_2 ) // DONE
            if ( t.rabbit.state instanceof RabbitClimbingRightContinue2State ) // DONE
            {
//                return RABBIT_ENTERING_EXIT_CLIMBING_RIGHT;
                return new RabbitEnteringExitClimbingRightState();
            }
//            return RABBIT_ENTERING_EXIT;
            return new RabbitEnteringExitState();
        }
        return null;
    }
}
