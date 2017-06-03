# Formula One Simulation

Test for Agoda SDE.
Test Coverage 90% lines

## Usage
- Language used for coding- Scala 2.11.11
- Build tool: Sbt
- Code will ask number of teams and lenght of track as input
- The output is printed to STDOUT.
```bash
$ sbt run
$ sbt test (to run test cases)
```

## Assumption

- Driver will only check in the front if there is any car within 10 meters, if so he will reduce the speed by handling factor


## Class Diagram

```
               +--------+                  +-----------------------+
               | Start  |----------------> |Read Input for the Race| 
               +--------+                  +------------------------
                 ^ ^ | |
Start Race       | | | |
                 | | v v
               +---------+                 +----------------+
               |   Race  |<--------------->|     Drivers     |
               |         |                 +----------------+
               +---------+                          |  Handles
                                                    |
                                           +--------v--------+
                                           |      Cars       |
                                           +----------------+
                                         
```

## Components
- Car
    - contains the specification of the car
    - increases the speed based on its specification
- Driver
    - Handles its car
    - Ask Race about its status and then instructs the car to perform the required       operations
- Race
    - Creates a list of drivers to simulate the race
    - Set the starting position of the drivers based on the algo
    - Instructs the drivers to drive for 2 secons until the race is not finished


