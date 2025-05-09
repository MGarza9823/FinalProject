import java.util.ArrayList;
import java.io.*;

public class InventoryManager
{
    private ArrayList<String> availableItems = new ArrayList<>();
    private String[] soldOutItems = new String[10];
    private int soldOutCount = 0;

    public void saveData(String availableFile, String soldOutFile)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(availableFile)))
        {
            for (String item : availableItems)
            {
                writer.write(item);
                writer.newLine();
            }
        }
        catch (IOException e)
        {
            System.out.println("Error saving available items: " + e);
        }

        try (BufferedWriter writer = new BufferedWriter (new FileWriter(soldOutFile)))
        {
            for (int i = 0; i < soldOutCount; i++)
            {
                writer.write(soldOutItems[i]);
                writer.newLine();
            }
        }
        catch (IOException e)
        {
            System.out.println("Error saving sold out items: " + e);
        }
    }

    public void loadData(String availableFile, String soldOutFile)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(availableFile)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                availableItems.add(line);
            }
        }
        catch (IOException e)
        {
            System.out.println("Error loading available items: " + e);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(soldOutFile)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
            if (soldOutCount < soldOutItems.length)
            {
                soldOutItems[soldOutCount++] = line;
            }
            }
    
        }
        catch (IOException e)
        {
            System.out.println("Errors loading sold out items: " + e);
        }
    }

public void addItem(String item)
{
    availableItems.add(item);
}

public void sellItem(int index) throws IndexOutOfBoundsException
{
    if (availableItems.isEmpty())
    {
        System.out.println("No items available to sell.");
        return;
    }
    if (index < 0 || index >= availableItems.size())
    {
        throw new IndexOutOfBoundsException("Invalid index.");
    }
    if (soldOutCount >= soldOutItems.length)
    {
        System.out.println("Sold out items list is full. Cannot sell more items.");
        return;
    }

    soldOutItems[soldOutCount++] = availableItems.remove(index);
    System.out.println("Item sold: " + soldOutItems[soldOutCount - 1]);
}

public void viewInventory()
{
    System.out.println("Available Items: " + availableItems);
    System.out.println("Sold Out Items: ");
    for (int i = 0; i < soldOutCount; i++)
    {
        System.out.println(soldOutItems[i]);
    }
    System.out.println();
}

public int getAvailableItemCount()
{
    return availableItems.size();
}


}