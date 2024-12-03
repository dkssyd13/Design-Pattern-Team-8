package rabbitescape.engine.state.entering;

import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.textworld.Chars;

public class RabbitEnteringExitState extends RabbitEnteringExitCommon
{
    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x, change.y, 'R' );
    }
}
