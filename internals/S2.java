import java.util.Scanner;

class Person {
    final static Scanner sc = new Scanner (System.in);

    String name;
    double height, weight, bmi;

    Person() {}

    public Person(double height, String name, double weight) {
        this.height = height;
        this.name = name;
        this.weight = weight;
        bmi = getBMI();
    }

    private double getBMI() {
        return (weight / (height * height));
    }

    void read() {
        System.out.println("Enter Person Details --->");

        System.out.print("Name: ");
        name = sc.nextLine();

        System.out.print("Height: ");
        height = sc.nextDouble();

        System.out.print("Weight: ");
        weight = sc.nextDouble();

        sc.nextLine();

        bmi = getBMI();
    }

    boolean isGreater(Person p) {
        return (this.name.compareTo(p.name) > 0);
    }

    boolean isGreater(Person p, String onAttribute) throws Exception {
        
        switch(onAttribute.trim().toLowerCase()) {
            case "":
            case "name":
                return isGreater(p);
            
            case "height":
                return (this.height > p.height);
            
            case "weight":
                return (this.weight > p.weight);

            case "bmi":
                return (this.bmi > p.bmi);

            default:
                throw new Exception ("Invalid Parameter: " + onAttribute);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Person{");
        sb.append("\n\tName =").append(name);
        sb.append("\n\tHeight =").append(height);
        sb.append("\n\tWeight =").append(weight);
        sb.append("\n\tBMI =").append(bmi);
        sb.append('}');

        return sb.toString();
    }

    
}

class S2 {
    public static void main (String[] args) {
        Person[] person = new Person[3];

        for (int i=0; i<person.length; i++) {
            person[i] = new Person();
            person[i].read();
        }

        for(Person p : person) {
            System.out.println(p);
        }

        for (String key : new String[] {"name", "height", "weight", "bmi"}) {
            System.out.println("\nSorted Array based on attribute: " + key + "\n-----------------------------------------");
            for (Person p : sort(person, key)) {
                System.out.println(p);
            }
        }
    }

    static Person[] sort(Person[] P, String onAttribute) {
        for (int i=0; i<P.length - 1; i++) {
            for (int j=0; j<P.length - i - 1; j++) {
                try {
                    if (P[j].isGreater(P[j+1], onAttribute)) {
                        Person temp = P[j];
                        P[j] = P[j+1];
                        P[j+1] = temp;
                    }                    
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        }

        return P;
    }
}