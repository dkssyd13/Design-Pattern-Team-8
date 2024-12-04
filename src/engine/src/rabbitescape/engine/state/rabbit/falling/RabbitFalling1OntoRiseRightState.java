package rabbitescape.engine.state.rabbit.falling;

import rabbitescape.engine.Behaviour;
import rabbitescape.engine.ChangeDescription;
import rabbitescape.engine.Rabbit;
import rabbitescape.engine.World;
import rabbitescape.engine.behaviours.Falling;
import rabbitescape.engine.textworld.Chars;

public class RabbitFalling1OntoRiseRightState extends RabbitFallingCommon
{
    @Override
    public boolean moveRabbit(
        World world,
        Rabbit rabbit,
        Behaviour behaviour
    )
    {
        Falling fallingBehavior = (Falling) behaviour;
        fallingBehavior.setHeightFallen( fallingBehavior.getHeightFallen() + 1 );
        rabbit.y = rabbit.y + 1;
        return true;
    }

    @Override
    public String name()
    {
        return "RABBIT_FALLING_1_ONTO_RISE_RIGHT";
    }

    @Override
    public void charForChange( ChangeDescription.Change change, Chars chars )
    {
        chars.set( change.x, change.y + 1, 'h' );
    }
}
