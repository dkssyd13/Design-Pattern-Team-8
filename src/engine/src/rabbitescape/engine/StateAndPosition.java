package rabbitescape.engine;

import rabbitescape.engine.state.State;

public class StateAndPosition
{
    public final State state;
    public final int x;
    public final int y;

    public StateAndPosition( State state, int x, int y )
    {
        this.state = state;
        this.x = x;
        this.y = y;
    }
}
