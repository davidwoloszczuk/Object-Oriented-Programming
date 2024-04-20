import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Nodes nodes = new Nodes();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Menu:");
            System.out.println("1. Fill");
            System.out.println("2. Clear");
            System.out.println("3. Count Nodes");
            System.out.println("4. Count ThreeDNodes");
            System.out.println("5. Sort");
            System.out.println("6. Display");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the size: ");
                    int size = scanner.nextInt();
                    try {
                        nodes.fill(size);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    nodes.clear();
                    System.out.println("Nodes cleared.");
                    break;
                case 3:
                    System.out.println("Number of Nodes: " + nodes.countNodes());
                    break;
                case 4:
                    System.out.println("Number of ThreeDNodes: " + nodes.countThreeDNodes());
                    break;
                case 5:
                    nodes.sort();
                    System.out.println("Nodes sorted.");
                    break;
                case 6:
                    System.out.println(nodes.toString());
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        } while (choice != 7);

        scanner.close();
    }
}