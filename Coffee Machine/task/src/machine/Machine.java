package machine;

import java.util.Scanner;

public class Machine {

    int water;
    int milk;
    int coffee;
    int cups;
    int money;
    Scanner sc = new Scanner(System.in);
    StateOfMachine stateOfMachine;

    public Machine() {
        this.water = 400;
        this.milk = 540;
        this.coffee = 120;
        this.cups = 9;
        this.money = 550;
        this.stateOfMachine = StateOfMachine.READY;
    }

    public void on() {
        while (true) {
            gui();
        }
    }

    public String input() {
        return sc.nextLine();
    }

    public void report() {
        System.out.printf("\nThe coffee machine has:\n");
        System.out.printf("%d of water\n", water);
        System.out.printf("%d of milk\n", milk);
        System.out.printf("%d of coffee beans\n", coffee);
        System.out.printf("%d of disposable cups\n", cups);
        System.out.printf("%d of money\n", money);
        System.out.println();
    }

    private void gui() {
//        System.out.println("Machine's state:"+stateOfMachine.name());

        if (stateOfMachine.name() == "READY") {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            stateOfMachine = StateOfMachine.valueOf(input().toUpperCase());
        }
        if (stateOfMachine.name() == "BUY") {
            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
            switch (input()) {
                case "1":
                    buyCoffee(250, 0, 16, 1, 4);
                    break;
                case "2":
                    buyCoffee(350, 75, 20, 1, 7);
                    break;
                case "3":
                    buyCoffee(200, 100, 12, 1, 6);
                    break;
                case "back":
                    stateOfMachine = StateOfMachine.READY;
                    break;
                default:
                    System.out.println("Bad command");
                    break;
            }
            stateOfMachine = StateOfMachine.READY;
        }
        if (stateOfMachine.name() == "FILL") {
            fill();
            stateOfMachine = StateOfMachine.READY;
        }
        if (stateOfMachine.name() == "TAKE") {
            take();
            stateOfMachine = StateOfMachine.READY;
        }
        if (stateOfMachine.name() == "REMAINING") {
            report();
            stateOfMachine = StateOfMachine.READY;
        }
        if (stateOfMachine.name() == "EXIT") {
            System.exit(0);
        }
    }

    public void buyCoffee(int waterIN, int milkIN, int coffeeIN, int cupsIN, int moneyIN) {

        boolean result = true;
        if (waterIN > water) {
            System.out.println("Sorry, not enough water!");
            result = false;
        }
        if (milkIN > milk) {
            System.out.println("Sorry, not enough milk!");
            result = false;
        }
        if (coffeeIN > coffee) {
            System.out.println("Sorry, not enough coffee!");
            result = false;
        }
        if (cupsIN > cups) {
            System.out.println("Sorry, not enough cups!");
            result = false;
        }
        if (result) {
            water -= waterIN;
            milk -= milkIN;
            coffee -= coffeeIN;
            cups -= cupsIN;
            money += moneyIN;
            System.out.println("I have enough resources, making you a coffee!");
        }
    }

    private void take() {
        System.out.printf("I gave you $%d\n", money);
        money = 0;
    }

    private void fill() {
        System.out.println("Write how many ml of water do you want to add:");
        water += Integer.parseInt(input());
        System.out.println("Write how many ml of milk do you want to add:");
        milk += Integer.parseInt(input());
        System.out.println("Write how many grams of coffee beans do you want to add:");
        coffee += Integer.parseInt(input());
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        cups += Integer.parseInt(input());
    }
}