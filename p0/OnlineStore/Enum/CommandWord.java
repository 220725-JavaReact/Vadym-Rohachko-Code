package Enum;

public enum CommandWord {
    INSER("insert"),
    DELETE("delete"),
    UPDATE("update"),
    SELECT("select"),
    HELP("help"),
    SUCCESS("success"),
    FAILURE("failure"),
    CANCEL("cancel"),
    FAILURE_DUE_TO_HIGH_AMOUNT("failure_due_to_high_amount"),
    INCREASE_PLURAL("increase_plural"),
    INCREASE_SINGLAR("increase_singular"),
    DECREASE_PLURAL("decrease_plural"),
    DECREASE_SINGLAR("decrease_singular"),
    QUIT("quit");
    private String commandString;

    CommandWord(String commandString) {
        this.commandString = commandString;
    }

    public String toString() {
        return commandString;
    }
}


//    CommandWord cmd = CommandWord.HELP;
//        System.out.println(cmd.name());
//                System.out.println(cmd.ordinal());
//                //System.out.println(cmd.equals("HELP"));
//
//                CommandWord[] ca=CommandWord.values();
//                Arrays.stream(ca).forEach(System.out::println);
//
//    CommandWord command = CommandWord.GO;
//
//switch (commandWord) {
//        case GO:
//        goRoom(secondWord);
//        break;
//        case LOOK:
//        look();
//        break;
//        case TAKE:
//        takeItem(secondWord);
//        break;
//        case HELP:
//        printHelp();
//        break;
//        case QUIT:
//        quit(); break;
//        }
// if (command == CommandWord.QUIT)