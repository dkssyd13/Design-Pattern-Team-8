package rabbitescape.engine.state.rabbit.entering;

import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.textworld.Chars;

public class RabbitEnteringExitState extends RabbitEnteringExitCommon
{
    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x, change.y, 'R' );
    }

    @Override
    public String name()
    {
        return "RABBIT_ENTERING_EXIT";
    }
}
