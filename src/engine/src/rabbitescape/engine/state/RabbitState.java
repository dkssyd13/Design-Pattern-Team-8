package rabbitescape.engine.state;

import rabbitescape.engine.*;
import rabbitescape.engine.behaviours.Bridging;
import rabbitescape.engine.textworld.Chars;
import rabbitescape.engine.ChangeDescription.Change;
import rabbitescape.engine.util.Position;

public interface RabbitState {
    boolean rabbitIsFalling();
    boolean rabbitIsClimbing();
    boolean rabbitIsBashing();
    boolean isBlocking();
    boolean isDigging();
    boolean moveRabbit( World world, Rabbit rabbit, int heightFallen );
    void charForChange(Change change, Chars chars);
    ChangeDescription.State newState( BehaviourTools t, boolean triggered); // TODO : 인터페이스로 바꾸기
    boolean behave(World world, Rabbit rabbit);
//    boolean checkTriggered( Rabbit rabbit, World world ); // TODO : Falling의 checkTrigered 한번 더 확인. 없어도 구현 가능할 것 같음
    Position whereBridging( StateAndPosition change );
    char bridgingStage( ChangeDescription.State state );

    boolean moveRabbit(
        Bridging.BridgeType bridgeType,
        World world,
        Rabbit rabbit
    );
}
