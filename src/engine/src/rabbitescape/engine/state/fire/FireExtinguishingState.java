package rabbitescape.engine.state.fire;

import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.Fire;
import rabbitescape.engine.World;
import rabbitescape.engine.textworld.Chars;

public class FireExtinguishingState extends FireStateCommon
{
    @Override
    public void step( World world, Fire fire )
    {
        world.changes.removeFire( fire );
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {

    }

    @Override
    public String name()
    {
        return "FIRE_EXTINGUISHING";
    }
}
