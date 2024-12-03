package rabbitescape.engine.state.entering;

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
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {

    }
}
