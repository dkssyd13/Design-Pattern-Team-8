package rabbitescape.engine.state;

import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.textworld.Chars;

public interface State {
    void charForChange( ChangeDescription.Change change, Chars chars);
    // TODO : Nothing State -> charForChange() 단순 break역할함
}
