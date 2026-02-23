import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        LTM ltm1 = null, ltm2;
        UTM utm1 = null, utm2;

        int mainChoice, subChoice;

        do {
            System.out.println("\n===== MAIN MENU =====");
            System.out.println("1. Lower Triangular Matrix (LTM)");
            System.out.println("2. Upper Triangular Matrix (UTM)");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            mainChoice = sc.nextInt();

            switch (mainChoice) {

                /* ================= LTM MENU ================= */
                case 1:
                    do {
                        System.out.println("\n--- LTM MENU ---");
                        System.out.println("1. Create & Read LTM");
                        System.out.println("2. Display LTM");
                        System.out.println("3. Add LTMs");
                        System.out.println("4. Multiply LTMs");
                        System.out.println("5. Multiply with UTM");
                        System.out.println("6. Determinant");
                        System.out.println("0. Back");
                        System.out.print("Enter choice: ");
                        subChoice = sc.nextInt();

                        try {
                            switch (subChoice) {

                                case 1:
                                    System.out.print("Enter order: ");
                                    int n = sc.nextInt();
                                    ltm1 = new LTM(n);
                                    ltm1.read();
                                    break;

                                case 2:
                                    if (ltm1 != null)
                                        ltm1.display();
                                    else
                                        System.out.println("LTM not created!");
                                    break;

                                case 3:
                                    if (ltm1 == null) {
                                        System.out.println("Create LTM first!");
                                        break;
                                    }
                                    ltm2 = new LTM(ltm1.n);
                                    System.out.println("Enter second LTM:");
                                    ltm2.read();
                                    LTM ltmSum = ltm1.add(ltm2);
                                    System.out.println("Result:");
                                    ltmSum.display();
                                    break;

                                case 4:
                                    if (ltm1 == null) {
                                        System.out.println("Create LTM first!");
                                        break;
                                    }
                                    ltm2 = new LTM(ltm1.n);
                                    System.out.println("Enter second LTM:");
                                    ltm2.read();
                                    LTM ltmMul = ltm1.multiply(ltm2);
                                    System.out.println("Result:");
                                    ltmMul.display();
                                    break;
                                
                                case 5:
                                    if (ltm1 == null) {
                                        System.out.println("Create LTM first!");
                                        break;
                                    }

                                    System.out.print("Enter order: ");
                                    UTM utm = new UTM(sc.nextInt());
                                    
                                    if (utm.n != ltm1.n) {
                                        System.out.println("Incompatible for muliplication");
                                        break;
                                    }

                                    utm.read();
                                    Matrix prod = ltm1.multiply(utm);
                                    System.out.println("Resultant Matrix: ");
                                    prod.display();

                                    break;

                                case 6:
                                    if (ltm1 != null)
                                        System.out.println("Determinant = " + ltm1.determinant());
                                    else
                                        System.out.println("LTM not created!");
                                    break;

                                case 0:
                                    break;

                                default:
                                    System.out.println("Invalid choice!");
                            }
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }

                    } while (subChoice != 0);
                    break;

                /* ================= UTM MENU ================= */
                case 2:
                    do {
                        System.out.println("\n--- UTM MENU ---");
                        System.out.println("1. Create & Read UTM");
                        System.out.println("2. Display UTM");
                        System.out.println("3. Add UTMs");
                        System.out.println("4. Multiply UTMs");
                        System.out.println("5. Multiply with LTM");
                        System.out.println("6. Determinant");
                        System.out.println("0. Back");
                        System.out.print("Enter choice: ");
                        subChoice = sc.nextInt();

                        try {
                            switch (subChoice) {

                                case 1:
                                    System.out.print("Enter order: ");
                                    int n = sc.nextInt();
                                    utm1 = new UTM(n);
                                    utm1.read();
                                    break;

                                case 2:
                                    if (utm1 != null)
                                        utm1.display();
                                    else
                                        System.out.println("UTM not created!");
                                    break;

                                case 3:
                                    if (utm1 == null) {
                                        System.out.println("Create UTM first!");
                                        break;
                                    }
                                    utm2 = new UTM(utm1.n);
                                    System.out.println("Enter second UTM:");
                                    utm2.read();
                                    UTM utmSum = utm1.add(utm2);
                                    System.out.println("Result:");
                                    utmSum.display();
                                    break;

                                case 4:
                                    if (utm1 == null) {
                                        System.out.println("Create UTM first!");
                                        break;
                                    }
                                    utm2 = new UTM(utm1.n);
                                    System.out.println("Enter second UTM:");
                                    utm2.read();
                                    UTM utmMul = utm1.multiply(utm2);
                                    System.out.println("Result:");
                                    utmMul.display();
                                    break;
                                
                                case 5:
                                    if (utm1 == null) {
                                        System.out.println("Create UTM first!");
                                        break;
                                    }

                                    System.out.print("Enter order: ");
                                    LTM ltm = new LTM(sc.nextInt());
                                    
                                    if (utm1.n != ltm.n) {
                                        System.out.println("Incompatible for muliplication");
                                        break;
                                    }

                                    ltm.read();
                                    Matrix prod = utm1.multiply(ltm);
                                    System.out.println("Resultant Matrix: ");
                                    prod.display();

                                    break;

                                case 6:
                                    if (utm1 != null)
                                        System.out.println("Determinant = " + utm1.determinant());
                                    else
                                        System.out.println("UTM not created!");
                                    break;

                                case 0:
                                    break;

                                default:
                                    System.out.println("Invalid choice!");
                            }
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }

                    } while (subChoice != 0);
                    break;

                case 0:
                    System.out.println("Program terminated.");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (mainChoice != 0);
    }
}
