package rabbitescape.engine.state;

import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.textworld.Chars;

public class EntranceState implements State{
    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {

    }

    @Override
    public String name()
    {
        return "ENTRANCE";
    }
}
