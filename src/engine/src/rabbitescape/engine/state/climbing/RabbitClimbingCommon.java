package rabbitescape.engine.state.climbing;

import rabbitescape.engine.*;
import rabbitescape.engine.behaviours.Climbing;
import rabbitescape.engine.state.RabbitState;
import rabbitescape.engine.state.State;
import rabbitescape.engine.util.Position;


public abstract class RabbitClimbingCommon implements RabbitState
{
    @Override
    public boolean rabbitIsClimbing()
    {
        return true;
    }

    public State newState( BehaviourTools t, Behaviour behaviour ){
        return newStateNotClimbing( t, (Climbing )behaviour );
    }

    // 기존 newStateNotClimbing() 구현
    public State newStateNotClimbing( BehaviourTools t, Climbing behaviour )
    {
        int nextX = t.nextX();
        int nextY = t.nextY();
        Block nextBlock = t.world.getBlockAt( nextX, nextY );
        Block aboveBlock = t.world.getBlockAt( t.rabbit.x, t.rabbit.y - 1 );

        if ( !t.isRoof( aboveBlock ) && t.isWall( nextBlock ) )
        {
            return t.rl(
                new RabbitClimbingRightStartState(),
                new RabbitClimbingLeftStartState()
            );
        }

        return null;
    }

    // 기존 newStateNotClimbing() 구현
    protected State newStateStart( BehaviourTools t, Climbing behaviour )
    {
        Block endBlock = t.blockAboveNext();

        if ( t.isWall( endBlock ) )
        {
            return t.rl(
                new RabbitClimbingRightContinue2State(),
                new RabbitClimbingLeftContinue2State()
            );
        }
        else
        {
            return t.rl(
                new RabbitClimbingRightEndState(),
                new RabbitClimbingLeftEndState()
            );
        }
    }

    // 기존 newStateNotClimbing() 구현
    protected State newStateCont1(BehaviourTools t, Climbing behaviour)
    {
        return t.rl(
            new RabbitClimbingRightContinue2State(),
            new RabbitClimbingLeftContinue2State()
        );
    }

    // 기존 newStateNotClimbing() 구현
    protected State newStateCont2(BehaviourTools t, Climbing behaviour){

        Block aboveBlock = t.blockAbove();

        if ( t.isRoof( aboveBlock ) )
        {
            behaviour.setAbilityActive( false );
            return t.rl(
                new RabbitClimbingRightBangHeadState(),
                new RabbitClimbingLeftBangHeadState()
            );
        }

        Block endBlock = t.blockAboveNext();

        if ( t.isWall( endBlock ) )
        {
            return t.rl(
                new RabbitClimbingRightContinue1State(),
                new RabbitClimbingLeftContinue1State()
            );
        }
        else
        {
            return t.rl(
                new RabbitClimbingRightEndState(),
                new RabbitClimbingLeftEndState()
            );
        }
    }

    @Override
    public boolean rabbitIsFalling()
    {
        return false;
    }

    @Override
    public boolean rabbitIsDigging()
    {
        return false;
    }

    @Override
    public boolean rabbitIsBashing()
    {
        return false;
    }

    @Override
    public boolean isBlocking()
    {
        return false;
    }

    @Override
    public boolean isDigging()
    {
        return false;
    }

    @Override
    public boolean moveRabbit( World world, Rabbit rabbit, Behaviour behaviour )
    {
        return false;
    }

    @Override
    public Position whereBridging( int x, int y )
    {
        return null;
    }

    @Override
    public char bridgingStage( ChangeDescription.State state )
    {
        return ' ';
    }
}
