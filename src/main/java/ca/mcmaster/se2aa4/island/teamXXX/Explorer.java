package ca.mcmaster.se2aa4.island.teamXXX;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONTokener;

import ca.mcmaster.se2aa4.island.teamXXX.drone.Direction;
import ca.mcmaster.se2aa4.island.teamXXX.drone.Drone;
import ca.mcmaster.se2aa4.island.teamXXX.island.Island;
import ca.mcmaster.se2aa4.island.teamXXX.Handler;
//import ca.mcmaster.se2aa4.island.teamXXX.Test;

public class Explorer implements IExplorerRaid {
    private Drone drone;
    private Island island;
    private Handler handler;
    //private Test test;

    private final Logger logger = LogManager.getLogger();

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));

        this.handler = new Handler();
        //this.test = new Test();


        logger.info("** Initialization info:\n {}",info.toString(2));
        Direction heading = Direction.toDirection(info.getString("heading"));
        Integer batteryLevel = info.getInt("budget");
        this.drone = new Drone(batteryLevel, heading);
        this.island = new Island(drone);
        logger.info("The drone is facing {}", heading);
        logger.info("Battery level is {}", batteryLevel);
    }

    @Override
    public String takeDecision() {
        JSONObject decision = this.handler.makeDecision(this.drone, this.island);
        logger.info("** Decision: {}", decision.toString());
        
        return decision.toString();

        //JSONObject decision = new JSONObject();
        //decision.put("action", "scan");
    }

    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Response received:\n"+response.toString(2));
        Integer cost = response.getInt("cost");
        logger.info("The cost of the action was {}", cost);
        String status = response.getString("status");
        logger.info("The status of the drone is {}", status);
        JSONObject extraInfo = response.getJSONObject("extras");
        logger.info("Additional information received: {}", extraInfo);
        Data data = new Data(this.handler.getCommand(), cost, extraInfo, Boolean.parseBoolean(status));
        this.island.update(data);
        this.drone.updateBattery(data.getCost());
        logger.info("The battery level is now: {}", this.drone.getBattery());
       // logger.info("The creeks are: {}", this.island.getCreeks());
        //logger.info("The sites are: {}", this.island.getSites());
    }

    @Override
    public String deliverFinalReport() {
        logger.info("** Final report:");
        logger.info("The creeks are: {}", this.island.getCreeks());
        logger.info("The sites are: {}", this.island.getSites());
        return ("no creeks found");
    }

}

