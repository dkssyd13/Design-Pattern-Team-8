package rabbitescape.engine.state.rabbit;

import rabbitescape.engine.*;
import rabbitescape.engine.state.State;
import rabbitescape.engine.util.Position;

public interface RabbitState extends State
{
    boolean rabbitIsFalling();
    boolean rabbitIsClimbing();
    boolean rabbitIsDigging();
    boolean rabbitIsBashing();
    boolean isBlocking();
    boolean isDigging();
    boolean moveRabbit( World world, Rabbit rabbit, Behaviour behaviour );
    boolean behave(World world, Rabbit rabbit, Behaviour behaviour);
    Position whereBridging( int x, int y );
    char bridgingStage( State state );
}
