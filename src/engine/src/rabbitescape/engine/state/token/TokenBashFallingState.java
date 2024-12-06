package rabbitescape.engine.state.token;

import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.Token;
import rabbitescape.engine.World;
import rabbitescape.engine.textworld.Chars;

public class TokenBashFallingState extends TokenState
{
    @Override
    public void step( World world, Token token )
    {
        ++token.y;

        if ( token.y >= world.size.height )
        {
            world.changes.removeToken( token );
        }
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x, change.y + 1, 'f' );
    }

    @Override
    public String name()
    {
        return "TOKEN_BASH_FALLING";
    }
}
