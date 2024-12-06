package rabbitescape.engine;


import rabbitescape.engine.state.State;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains the monolothic enum of rabbit and token states.
 */
public class ChangeDescription
{

    public static class Change
    {
        public final int x;
        public final int y;
        public final State state;

        public Change( int x, int y, State state )
        {
            this.x = x;
            this.y = y;
            this.state = state;
        }

    }

    public final List<Change> changes = new ArrayList<>();

    public void add( int x, int y, State state )
    {
        changes.add( new Change( x, y, state ) );
    }
}
