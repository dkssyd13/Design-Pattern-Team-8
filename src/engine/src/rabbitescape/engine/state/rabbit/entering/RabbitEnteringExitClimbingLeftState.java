package rabbitescape.engine.state.rabbit.entering;

import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.textworld.Chars;

public class RabbitEnteringExitClimbingLeftState extends
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