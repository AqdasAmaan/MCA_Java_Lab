import java.util.Scanner;
import java.util.Vector;

class ToDoList {

    static Vector<String> tasks = new Vector<>() ;
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        int ch;
        
        do {
            System.out.print("1. Add a task \n2. Delete a task \n3. Show all tasks \n4. Exit \nYour choice: ") ;
            ch = sc.nextInt();
            sc.nextLine();

            switch(ch) {
                case 1:
                    System.out.print("Enter task: ");
                    tasks.add(sc.nextLine());
                    break;

                case 2:
                    System.out.print("\nEnter the task no. to delete it. ");
                    display();
                    System.out.print("Task no. to delete: ");
                    int x = sc.nextInt();
                    sc.nextLine();

                    if(x < 0 || x >= tasks.size()) {
                        System.out.println("No such task available!\n------------------------------------------------\n");
                    }
                    else {
                        System.out.println("Task Deleted: " + tasks.get(x-1));
                        tasks.removeElementAt(x-1);
                    }

                    break;

                case 3:
                    display();
                    break;

                case 4:
                    System.out.println("\nExit.");
                    break;

                default:
                    System.out.println("\nInvalid Choice!\n----------------------------------------\n");
            }
        } while (ch != 4);

        sc.close();
    }

    public static void display() {
        int i = 0;
        System.out.println("\nTasks -->");
        for(String task : tasks) {
            System.out.println((i+1) + ". " + task);
            i++;
        }
        System.out.println("-----------------------------------------\n");
    }

}