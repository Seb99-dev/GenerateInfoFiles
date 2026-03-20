import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * This class generates all input files required for the project.
 * It creates:
 * - Salesmen information file
 * - Products information file
 * - Individual sales files per salesman
 */
public class GenerateInfoFiles {

    static String[] firstNames = {"Juan", "Maria", "Carlos", "Ana", "Luis", "Pedro", "Sofia", "Diego", "Laura", "Andres"};
    static String[] lastNames = {"Perez", "Gomez", "Lopez", "Rodriguez", "Martinez", "Ramirez", "Torres", "Diaz", "Castro", "Vargas"};
    static String[] productNames = {"Rice", "Milk", "Bread", "Eggs", "Coffee", "Sugar", "Salt", "Oil", "Cheese", "Chicken"};

    public static void main(String[] args) {

        try {
            createFolder();

            // Create input files
            createSalesManInfoFile(10);
            createProductsFile(10);

            // Create bucle
        int totalSalesmen = 10;
        for (int i = 0; i < totalSalesmen; i++) {
            long id = 1001 + i;
            createSalesMenFile(10, "Salesman" + i, id);
        }


            System.out.println("Files generated successfully ✅");

        } catch (Exception e) {
            System.out.println("Error generating files ❌");
            e.printStackTrace();
        }
    }

    /**
     * Creates the directory where all files will be stored
     */
    public static void createFolder() {
        File folder = new File("data");
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    /**
     * Creates the salesmen information file
     * @param salesmanCount number of salesmen
     */
    public static void createSalesManInfoFile(int salesmanCount) throws IOException {

        FileWriter writer = new FileWriter("data/salesmen.txt");

        int baseId = 1001;

        for (int i = 0; i < salesmanCount; i++) {

            String firstName = firstNames[i % firstNames.length];
            String lastName = lastNames[i % lastNames.length];

            writer.write("CC;" + (baseId + i) + ";" + firstName + ";" + lastName + "\n");
        }

        writer.close();
    }

    /**
     * Creates the products file with random prices
     * @param productsCount number of products
     */
    public static void createProductsFile(int productsCount) throws IOException {

        FileWriter writer = new FileWriter("data/products.txt");
        Random random = new Random();

        for (int i = 0; i < productsCount; i++) {

            int price = (random.nextInt(20) + 1) * 1000;
            String productName = productNames[i % productNames.length];

            writer.write((i + 1) + ";" + productName + ";" + price + "\n");
        }

        writer.close();
    }

    /**
     * Creates a sales file for a specific salesman
     * @param randomSalesCount number of products sold
     * @param name salesman name (not used but required by specification)
     * @param id salesman ID
     */
    public static void createSalesMenFile(int randomSalesCount, String name, long id) throws IOException {

        FileWriter writer = new FileWriter("data/sales_" + id + ".txt");
        Random random = new Random();

        // Header
        writer.write("CC;" + id + "\n");

        // Sales records (Based on real products)
        for (int i = 1; i <= productNames.length; i++) {

            int quantity = random.nextInt(10) + 1;

            if (quantity > 0) {
                writer.write(i + ";" + quantity + ";\n");
            }
        }

        writer.close();
    }
}