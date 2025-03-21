package ca.mcmaster.se2aa4.island.teamXXX.island;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Drone;
import ca.mcmaster.se2aa4.island.teamXXX.island.Island;
import ca.mcmaster.se2aa4.island.teamXXX.Handler;
import ca.mcmaster.se2aa4.island.teamXXX.island.Signal;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Commands;

import org.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Navigate {
    private boolean groundFound = false;
    private Integer iteration = 0;
    private Integer outOfRangeIteration = 0;
    private Integer groundIteration = 0;


    public Navigate() {}

    private final Logger logger = LogManager.getLogger();

    public JSONObject search(Drone drone, Island island, Handler handler) {
        if (this.iteration == 0) {

            this.iteration++;
            handler.setCommand(Commands.ECHOFORWARD);
            return drone.echoForward();

        } else if (island.getForwardRange() > 0 && island.getForward() == Signal.OUTOFRANGE) {
            if (this.outOfRangeIteration == 0) {
                // First fly forward
                this.outOfRangeIteration++;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            } else if (this.outOfRangeIteration == 1) {
                // Then scan
                this.outOfRangeIteration++;
                handler.setCommand(Commands.FLY);
                return drone.fly();
            }else if (this.outOfRangeIteration == 2) {
                // Then scan
                this.outOfRangeIteration++;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            } else if (this.outOfRangeIteration == 3) {
                //now echo right
                this.outOfRangeIteration++;
                handler.setCommand(Commands.ECHORIGHT);
                return drone.echoRight();
            } else if (this.outOfRangeIteration == 4) {
                //now echo left
                this.outOfRangeIteration++;
                handler.setCommand(Commands.ECHOLEFT);
                return drone.echoLeft();
            } else if (this.outOfRangeIteration == 5) {
                this.outOfRangeIteration = 0;
                this.iteration = 0;
                if (island.getLeftRange() > island.getRightRange()) {
                    handler.setCommand(Commands.TURNLEFT);
                    return drone.turnLeft();
                } else {
                    handler.setCommand(Commands.TURNRIGHT);
                    return drone.turnRight();
                }
            }

        } else if (island.getForwardRange() > 0 && island.getForward() == Signal.GROUND) {
            if (this.groundIteration == 0) {
                this.groundIteration++;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            } else if (this.groundIteration < island.getForwardRange() + 1) {
                this.groundIteration++;
                handler.setCommand(Commands.FLY);
                return drone.fly();
            } else {
                this.groundIteration = 0;
                handler.setCommand(Commands.ECHOFORWARD);
                return drone.echoForward();
            }
        } else if (island.getForwardRange() == 0 && island.getForward() == Signal.GROUND && !groundFound) {
            if (this.groundIteration == 0) {
                this.groundIteration++;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            } else if (this.groundIteration == 1) {
                this.groundIteration++;
                handler.setCommand(Commands.FLY);
                logger.info("ground found");
                groundFound = true;
                return drone.fly();
            }else if (this.groundIteration == 2) {
                this.groundIteration++;
                handler.setCommand(Commands.SCAN);
                return drone.scan();
            }
        } else {
            logger.info("found: {}", island.getForward().toString());
            logger.info("range: {}", island.getForwardRange().toString());
            handler.setCommand(Commands.STOP);
            return drone.stop();
        }
        return handler.makeDecision(drone, island);
    }
    
}
