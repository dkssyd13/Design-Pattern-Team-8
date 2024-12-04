package rabbitescape.engine.state.rabbit.entering;

import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.textworld.Chars;

public class RabbitEnteringExitClimbingRightState extends
    RabbitEnteringExitCommon
{
    @Override
    public boolean rabbitIsClimbing()
    {
        return true;
    }

    @Override
    public String name()
    {
        return "RABBIT_ENTERING_EXIT_CLIMBING_RIGHT";
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {

    }
}
