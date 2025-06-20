# Rescue mission
SFWRENG 2AA4 - Introduction to Software Development 

## Project Description

This product is an _exploration command center_ for the [Island](https://ace-design.github.io/island/) serious game. 

This project is a Rescue Drone System. 
This system supports rescue missions of individuals by 
(1) locating people in unknown terrain 
(2) finding a safe place where a rescue team can be sent close to the people to rescue.

Based on satellite imaging, we know that there is an island more or less in the center of the search
area (a rectangular grid). We also know that people took shelter somewhere on the island in a place
we’ll call the Emergency Site. 
The island’s coast is not easy to approach, and a rescue mission coming
by boat will only be able to land at some specific inlets (called Creeks).

### MISSION OBJECTIVE
Your software must send the command to the drone to find the emergency site and a creek (inlet) that
can be used to send a boat to rescue the people at the emergency site. This must be achieved by
consuming as little energy as possible

### Constraints

Your drone has three (3) limitations that could end up in a Missing In Action (MIA) situation:

• Battery capacity. Each drone has a specific battery capacity that should be sufficient to carry
out a rescue mission. If the drone runs out of battery before returning to base, you lose it.
• Radio range. The drones are radio-piloted, and the radio antenna only covers the rescue
zone. If the drone flies out of range, you lose it.
• Incorrect command. Sending an incorrect control command might damage the drone. For
example, if the drone faces East and you ask it to turn West immediately, it will flip over and
crash. To do a proper U-turn, you must first turn south (or north) and then west. 



### Compiling the project:

```mvn clean package```

### Run the project

Execution for testing purposes:

```mvn exec:java -q -Dexec.args="./maps/map03.json"```

It creates three files in the `outputs` directory:

- `_pois.json`: the location of the points of interests
- `Explorer_Island.json`: a transcript of the dialogue between the player and the game engine
- `Explorer.svg`: the map explored by the player, with a fog of war for the tiles that were not visited.
- 
## Technologies used
Java, JUnit, Maven, JSON 
