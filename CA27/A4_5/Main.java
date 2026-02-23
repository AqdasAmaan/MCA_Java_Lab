import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the no. of nodes to be created initially: ");
        int n = sc.nextInt();

        LinkedList list = new LinkedList();
        list.create(n);

        int ch;
        do {
            System.out.print("\n\n--------MENU--------\n1. Insert \n2. Delete \n3. Size \n4. Search An Element \n5. Get Element At Index \n6. Sort \n7. Display \n0. Exit \n\nYour Choice: ");
            ch = sc.nextInt();
            try {
                switch (ch) {

                    case 1:
                        System.out.print("Enter element to insert: ");
                        int x = sc.nextInt();
                        System.out.print("Enter index: ");
                        int idx = sc.nextInt();
                        list.insert(x, idx);
                        System.out.println("Element inserted.");
                        break;

                    case 2:
                        System.out.print("Enter index to delete: ");
                        int delIdx = sc.nextInt();
                        int deleted = list.del(delIdx);
                        System.out.println("Deleted element: " + deleted);
                        break;

                    case 3:
                        System.out.println("Size of linked list: " + list.size());
                        break;

                    case 4:
                        System.out.print("Enter element to search: ");
                        int elem = sc.nextInt();
                        int index = list.indexOf(elem);

                        if (index == -1)
                            System.out.println("Element not found.");
                        else   
                            System.out.println("Element found at index " + index + ".");

                    case 5:
                        System.out.print("Enter index: ");
                        int getIdx = sc.nextInt();
                        System.out.println("Element at index " + getIdx + ": " + list.get(getIdx));
                        break;

                    case 6:
                        int ch_sort;
                        boolean success = false;

                        do {
                            System.out.println("-------Sorting Options------- \n1. Bucket Sort \n2. Radix Sort \n0. Go back \nYour choice: ");
                            ch_sort = sc.nextInt();

                            switch (ch_sort) {
                                case 1 -> {
                                    System.out.println("Sorting using bucket sort...");
                                    list.sort("bucket");
                                    success = true;
                                }

                                case 2 -> {
                                    System.out.println("Sorting using radix sort...");
                                    list.sort("radix");
                                    success = true;
                                }

                                case 0 -> {
                                    System.out.println("Going back to previous menu...");
                                }

                                default -> {
                                    System.out.println("Invalid Option!");
                                }
                            }

                        } while (ch_sort != 0 || !success);
                        
                        break;

                    case 7:
                        System.out.print("Linked List: ");
                        list.display();
                        break;

                    case 0:
                        System.out.println("Program Terminated...!");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                }
            } 
            
            catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            
        }
        while (ch != 0);
    } 
}