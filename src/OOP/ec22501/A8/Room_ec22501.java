package OOP.ec22501.A8;

import java.util.Random;
class Room_ec22501 extends Room {

    private boolean inRoom = true;
    static final Item Axe = new Item("Axe...");

    public Direction visit(Visitor adventurer, Direction directionFrom) {

        boolean hasAxe = adventurer.hasIdenticalItem(Axe);

        Direction exit = directionFrom;

        if (directionFrom == Direction.FROM_NORTH) {
            adventurer.tell("You have arrived from the north.");
        } else if (directionFrom == Direction.FROM_EAST) {
            adventurer.tell("You have arrived from the east.");
        } else if (directionFrom == Direction.FROM_SOUTH) {
            adventurer.tell("You have arrived from the south.");
        } else if (directionFrom == Direction.FROM_WEST) {
            adventurer.tell("You have arrived from the west.");
        }

        adventurer.tell("How did you get here? Are you in the right place?");
        adventurer.tell("Please leave");

        while (inRoom = true) {
            final String chooseAction = ("('A')leave as instructed ('B')flip light switch ('C')light candle");
            char[] options1 = {'A', 'B', 'C'};
            char[] options2 = {'Y', 'N'};

            char firstChoice = adventurer.getChoice(chooseAction, options1);

            if (firstChoice == 'A') {
                adventurer.tell("Thank you, I hope you find your way.");

                inRoom = false;

                return randomDoor(exit, adventurer);
            } else if (firstChoice == 'B') {
                adventurer.tell("You tried to flip the switch...");
                adventurer.tell("It did nothing.");

                char secondChoice = adventurer.getChoice(chooseAction, options1);

                if (secondChoice == 'A') {
                    adventurer.tell("Thank you, I hope you find your way.");

                    inRoom = false;

                    return randomDoor(exit, adventurer);
                } else if (secondChoice == 'B') {
                    adventurer.tell("You tried to flip the switch... again...");
                    adventurer.tell("Please stop.");

                    char thirdChoice = adventurer.getChoice(chooseAction, options1);

                    if (thirdChoice == 'A') {
                        adventurer.tell("Thanks... good luck on your journey.");

                        inRoom = false;

                        return randomDoor(exit, adventurer);
                    } else if (thirdChoice == 'B') {
                        adventurer.tell("NO MORE!");

                        adventurer.takeGold(10);

                        inRoom = false;

                        return randomDoor(exit, adventurer);
                    } else if (thirdChoice == 'C') {
                        adventurer.tell("Good idea... maybe the switch doesn't work...");
                        adventurer.tell("You light the candle, you can now see the floor around you and an axe.");
                        adventurer.tell("Do you pick it up?");
                        adventurer.giveItem(new Item("Axe"));

                    }
                } else if (secondChoice == 'C') {
                    adventurer.tell("You light the candle, you can now see the floor around you and an axe.");
                    adventurer.tell("Do you pick it up?");
                    adventurer.giveItem(new Item("Axe"));
                }
            } else if (firstChoice == 'C') {
                adventurer.tell("You light the candle, you can now see the floor around you and an axe.");
                adventurer.tell("Do you pick it up?");
                adventurer.giveItem(new Item("Axe"));
            }
        }
        return exit;
    }


    public static int getRandomNumberInRange (int min, int max) {
        Random random = new Random();

        return random.nextInt((max - min) + 1) + min;
    }


    public static Direction randomDoor (Direction exit, Visitor adventurer) {
        int randomNumber = getRandomNumberInRange(1, 4);

        if (randomNumber == 1) {
            adventurer.tell("You have left through the North door.");
            exit = Direction.TO_NORTH;
        } else if (randomNumber == 2) {
            adventurer.tell("You have left through the East door.");
            exit = Direction.TO_EAST;
        } else if (randomNumber == 3) {
            adventurer.tell("You have left through the South door.");
            exit = Direction.TO_SOUTH;
        } else {
            adventurer.tell("You have left through the West door.");
            exit = Direction.TO_WEST;
        }

        return exit;
    }
}
