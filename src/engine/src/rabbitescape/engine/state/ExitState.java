package rabbitescape.engine.state;

import rabbitescape.engine.ChangeDescription;
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
}
