package machine;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class CoffeeMachine {

    final static Resource resource = new Resource(400,540,120,9,550);
    final static Scanner scanner = new Scanner(System.in);
    static int cupCountForCleaning;
    final static int MAXIMUM_CUPS_FOR_CLEANING = 10;


    public static void displayCurrentStatus() {
        System.out.println();
        var status = """                
                The coffee machine has:
                %d ml of water
                %d ml of milk
                %d g of coffee beans
                %d disposable cups
                $%.0f of money
                """.formatted(resource.getWater(),resource.getMilk(),
                resource.getBeans(),resource.getCup(),resource.getBalance());
        System.out.println(status);
    }

    public static void actionsMenu() {
        do {
            System.out.println("Write action (buy, fill, take, clean, remaining, exit):");
            var option = scanner.next().toLowerCase();
            switch (option) {
                case "buy" -> buyAction();
                case "fill" -> fillAction();
                case "take" -> takeAction();
                case "clean" -> cleanAction();
                case "remaining" -> displayCurrentStatus();
                case "exit" -> { return; }
                default -> System.out.println("Unexpected value: " + option);
            }
        } while (true);
    }

    public static void buyAction() {

        if (isCleaningRequired()) {
            System.out.println("I need cleaning!");
            return;
        }
        System.out.println();
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        var option = scanner.next();
        switch (option) {
            case "1" -> processBuyRequest(CoffeeTypes.ESPRESSO);
            case "2" -> processBuyRequest(CoffeeTypes.LATTE);
            case "3" -> processBuyRequest(CoffeeTypes.CAPPUCCINO);
            case "back" -> { return; }
        }

    }

    public static void fillAction() {
        for (var v: buildMessagePrompt().entrySet()) {
            System.out.println(v.getValue());
            resource.ops(v.getKey(), scanner.nextInt());
        }

    }

    public static void takeAction() {
        System.out.printf("I gave you $%.0f%n", resource.getBalance());
        resource.setBalance(-resource.getBalance());
    }

    public static void cleanAction() {
        System.out.println("I have been cleaned!");
        cupCountForCleaning = 0;
    }

    public static boolean isCleaningRequired() {
        return cupCountForCleaning >= MAXIMUM_CUPS_FOR_CLEANING;
    }

    public static void updateCupCount() {
        cupCountForCleaning++;
    }

    public static Map<String,String> buildMessagePrompt() {
        var message = """                
                Write how many ml of water you want to add:
                Write how many ml of milk you want to add:
                Write how many grams of coffee beans you want to add:
                Write how many disposable cups you want to add:""".split("\n");

        var resource = "water,milk,beans,cup".split(",");
        var resourceMessage = new LinkedHashMap<String,String>();
        for (int i = 0; i < Math.min(message.length, resource.length); i++) {
            resourceMessage.put(resource[i],message[i]);
        }
        return resourceMessage;
    }

    public static void processBuyRequest(CoffeeTypes type) {
        var resourceStatus = resource.checkResource(type);
        var status = switch (resourceStatus) {
            case "enough" -> {
                updateCupCount();
                yield  "I have enough resources, making you a coffee!";
            }
            case "water", "beans", "milk" -> "Sorry, not enough %s!".formatted(resourceStatus);
            default -> "Invalid option";
        };
        System.out.println(status);
        if ( resourceStatus.equals("enough") ) resource.updateResource(type);
    }

    public static void main(String[] args) {
        actionsMenu();

    }


}