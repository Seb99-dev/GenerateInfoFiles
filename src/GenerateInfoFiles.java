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

    private static final String DATA_FOLDER = "data";
    private static final String SALESMEN_FILE = DATA_FOLDER + "/salesmen.txt";
    private static final String PRODUCTS_FILE = DATA_FOLDER + "/products.txt";

    private static final int TOTAL_SALESMEN = 10;
    private static final int TOTAL_PRODUCTS = 10;
    private static final int SALES_PER_SALESMAN = 10;
    private static final int BASE_SALESMAN_ID = 1001;

    private static final Random RANDOM = new Random();

    static String[] firstNames = {
        "Juan", "Maria", "Carlos", "Ana", "Luis",
        "Pedro", "Sofia", "Diego", "Laura", "Andres"
    };

    static String[] lastNames = {
        "Perez", "Gomez", "Lopez", "Rodriguez", "Martinez",
        "Ramirez", "Torres", "Diaz", "Castro", "Vargas"
    };

    static String[] productNames = {
        "Rice", "Milk", "Bread", "Eggs", "Coffee",
        "Sugar", "Salt", "Oil", "Cheese", "Chicken"
    };

    public static void main(String[] args) {
        try {
            createFolder();
            createSalesManInfoFile(TOTAL_SALESMEN);
            createProductsFile(TOTAL_PRODUCTS);

            for (int i = 0; i < TOTAL_SALESMEN; i++) {
                long id = BASE_SALESMAN_ID + i;
                createSalesMenFile(SALES_PER_SALESMAN, "Salesman" + i, id);
            }

            System.out.println("Files generated successfully ✅");
        } catch (Exception e) {
            System.out.println("Error generating files ❌");
            e.printStackTrace();
        }
    }

    /**
     * Creates the directory where all files will be stored.
     */
    public static void createFolder() {
        File folder = new File(DATA_FOLDER);
        if (!folder.exists()) {
            boolean created = folder.mkdir();
            if (!created) {
                System.out.println("Warning: data folder could not be created.");
            }
        }
    }

    /**
     * Creates the salesmen information file.
     * @param salesmanCount number of salesmen
     */
    public static void createSalesManInfoFile(int salesmanCount) throws IOException {
        FileWriter writer = new FileWriter(SALESMEN_FILE);

        for (int i = 0; i < salesmanCount; i++) {
            String firstName = firstNames[RANDOM.nextInt(firstNames.length)];
            String lastName = lastNames[RANDOM.nextInt(lastNames.length)];

            writer.write("CC;" + (BASE_SALESMAN_ID + i) + ";" + firstName + ";" + lastName + "\n");
        }

        writer.close();
    }

    /**
     * Creates the products file with random prices.
     * @param productsCount number of products
     */
    public static void createProductsFile(int productsCount) throws IOException {
        FileWriter writer = new FileWriter(PRODUCTS_FILE);

        for (int i = 0; i < productsCount; i++) {
            int price = (RANDOM.nextInt(20) + 1) * 1000;
            String productName = productNames[i % productNames.length];

            writer.write((i + 1) + ";" + productName + ";" + price + "\n");
        }

        writer.close();
    }

    /**
     * Creates a sales file for a specific salesman.
     * @param randomSalesCount number of sales records to generate
     * @param name salesman name (required by specification)
     * @param id salesman ID
     */
    public static void createSalesMenFile(int randomSalesCount, String name, long id) throws IOException {
        FileWriter writer = new FileWriter(DATA_FOLDER + "/sales_" + id + ".txt");

        writer.write("CC;" + id + "\n");

        for (int i = 0; i < randomSalesCount; i++) {
            int productCode = RANDOM.nextInt(TOTAL_PRODUCTS) + 1;
            int quantity = RANDOM.nextInt(10) + 1;
            writer.write(productCode + ";" + quantity + ";\n");
        }

        writer.close();
    }
}