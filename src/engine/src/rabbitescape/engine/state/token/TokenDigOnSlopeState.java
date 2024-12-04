package rabbitescape.engine.state.token;

import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.Token;
import rabbitescape.engine.World;
import rabbitescape.engine.textworld.Chars;

public class TokenDigOnSlopeState implements TokenState{
    @Override
    public void step( World world, Token token )
    {

    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {

    }

    @Override
    public String name()
    {
        return "TOKEN_DIG_ON_SLOPE";
    }
}
