package rabbitescape.engine.state.token;

import rabbitescape.engine.Token;
import rabbitescape.engine.World;
import rabbitescape.engine.state.State;

public interface TokenState extends State
{
    void step( World world, Token token );
}
