import java.util.Scanner;

public class InventoryManagerRunner 
{
    private static InventoryManager manager;
    private static final Scanner kb = new Scanner(System.in);
    
    public static void main(String[] args)
    {
        clearConsole();
        manager = new InventoryManager();
        manager.loadData("availableItems.txt", "soldOutItems.txt");

        makeChoice();
    }

public static void makeChoice()
{
    int choice;
    do {
        mainMenu();
        choice = InputValidator.getValidInteger("Enter your choice (1-4): ", 1, 4);
        System.out.println("You chose option " + choice);
        menuChoice(choice);
        System.out.println("\u001B[32m" + "Press enter to continue..." + "\u001B[0m");
        clearConsole();
    }
    while (choice != 4);
}

public static void clearConsole()
{
    System.out.println("\033[H\033[2J");
    System.out.flush();
}

public static void menuChoice(int choice)
{
    switch (choice)
    {
        case 1:
            System.out.print("Enter the item to add: ");
            String newItem = kb.nextLine();
            manager.addItem(newItem);
            break;
        case 2:
            manager.viewInventory();
            if (manager.getAvailableItemCount() == 0)
            {
                System.out.println("No items to sell sorry.");
                break;
            }
            int itemIndex = InputValidator.getValidInteger("Enter the index of the item to sell: ", 0, manager.getAvailableItemCount() - 1);
            try {
                manager.sellItem(itemIndex);
            } 
            catch (IndexOutOfBoundsException e) 
            {
                System.out.println("Invalid index. Try again!");
            }
            break;
        case 3:
            manager.viewInventory();
            break;
        case 4:
            manager.saveData("availableItems.txt", "soldOutItems.txt");
            System.out.println("Data saved. Goodbye!");
            return;
    }
}

public static void mainMenu()
{
    System.out.println("Choose an option: ");
    System.out.println("1. Add Item");
    System.out.println("2. Sell Item");
    System.out.println("3. View Inventory");
    System.out.println("4. Exit");
}

}