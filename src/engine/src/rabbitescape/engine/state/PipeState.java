package rabbitescape.engine.state;

import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.state.State;
import rabbitescape.engine.textworld.Chars;

public class PipeState implements State {
    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x, change.y, 'P' );
    }

    @Override
    public String name()
    {
        return "PIPE";
    }
}
