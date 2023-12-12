import java.util.Random;
import java.util.Scanner;

public class Lab10 {
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        Q1();
        Q2();
        Q3();
        Q4();
        scan.close();
    }

    public static void Q1() {
        double side1,side2,r;
        
        while (true) {
            System.out.println("Pick a shape: square, rectangle, circle (or 'q' to quit)");
            String input = scan.nextLine();
            if (input.equals("q")) 
            {
                return;
            }

            if (input.equals("square")) 
            {
                
                System.out.println("Enter the length of the square: ");
                side1 = Double.parseDouble(scan.nextLine());
                System.out.println("The circumference of the square is: " + side1 * 4);
                System.out.println("The area of the square is: " + side1 * side1);

            } 
            else if (input.equals("rectangle")) 
            {
                
                System.out.println("Enter the length of side 1: ");
                side1 = Double.parseDouble(scan.nextLine());
                System.out.println("Enter the length of side 2: ");
                side2 = Double.parseDouble(scan.nextLine());
                System.out.println("The circumference of the rectangle is: " + (2 * side1 + 2 * side2));
                System.out.println("The area of the rectangle is: " + (side1 * side2));
            } 
            else if (input.equals("circle")) 
            {
                System.out.println("Enter the radius: ");
                r = Double.parseDouble(scan.nextLine());
                System.out.println("The circumference of the circle is: " + (Math.PI * r * 2));
                System.out.println("The area of the circle is: " + (Math.PI * r * r));
            }
        }
    }

    public static void Q2() 
    {
        System.out.println("Q2: Enter the current day (1-31): ");
        int day = Integer.parseInt(scan.nextLine());
        System.out.println("Enter the current month: (1-12)");
        int month = Integer.parseInt(scan.nextLine());
        String monthString = new String("");        
        String dayAppendix = new String("");
        int dayLastDigit = day % 10;

        if(dayLastDigit == 1 && day != 11)
        {
            dayAppendix = "st";
        }
        else if(dayLastDigit == 2 && day != 12)
        {
            dayAppendix = "nd";
        }
        else if(dayLastDigit == 3 && day != 13)
        {
            dayAppendix = "rd";
        }
        else
        {
            dayAppendix = "th";
        }

        switch(month)
        {
            case 1: monthString = "January";
            break;
            case 2: monthString = "February";
            break;
            case 3: monthString = "March";
            break;
            case 4: monthString = "April";
            break;
            case 5: monthString = "May";
            break;
            case 6: monthString = "June";
            break;
            case 7: monthString = "July";
            break;
            case 8: monthString = "August";
            break;
            case 9: monthString = "September";
            break;
            case 10: monthString = "October";
            break;
            case 11: monthString = "November";
            break;
            case 12: monthString = "December";
            break;
            default: System.out.println("Invalid month");
        }
        if(day < 31 && month < 13 )
        {
            System.out.println("You have selected " + day + dayAppendix + " of " + monthString);
        }
        else
        {
            System.out.println("Invalid date entered");
        }
    }

public static void Q3() 
    {
        System.out.println("Q3: Enter how many numbers you want to check for primality: ");
        int n = Integer.parseInt(scan.nextLine());
        int counter = 0;
        for (int i = 0; i < n; i++) 
        {
            if (i < 2)
            continue;
            boolean check = true;

            for (int j = 2; j * j <= i; j++) 
            {
                if (i % j == 0) 
                {
                    check = false;
                    break;
                }          
            }
            
            if (check == true) 
            {
            counter++;
            } 
        }

        System.out.println("There are: " + counter + " primes between 0 and " + n);
    }

    public static void Q4() {
        Random rng = new Random();

        String next;
        System.out.println("Q4: Let's play a game. Type \"A\" to attack, \"B\" to buff your next attack. Kill the enemy to win!");
        System.out.println("Q4: You must roll higher than the enemy armor class (12) to hit. Roll 20 for a critical hit!");
        System.out.println("Q4: Your damage is 2-16 (2d8)");

        int enemyHP = 100;
        int a = 0;

        boolean check = false;
        while (true) {

            boolean doAttack = false;
            boolean check2 = false;
            while (!check2) {
                next = scan.nextLine();
                check2 = true;
                switch (next) {
                    case "A", "a":
                        doAttack = true;
                        break;
                    case "B", "b":
                        check = true;
                        System.out.println("Buffing! +5 to your next attack roll and damage");
                        break;
                    default:
                        System.out.println("Invalid input");
                        check2 = false;
                }
            }

            if (doAttack) {
                a++;
                int attackRoll = rng.nextInt(20) + 1;
                int damage = 0;
                System.out.print("You rolled: " + attackRoll);
                if(check) {
                    attackRoll += 5;
                    System.out.print(" + 5 (buff active)\n");
                } else {
                    System.out.println();
                }
                if (attackRoll >= 12) {
                    damage = rng.nextInt(8) + 1;
                    damage += rng.nextInt(8) + 1;
                    if(check) {
                        damage += 5;
                    }
                    if (attackRoll == 20 || (check && attackRoll == 20 + 5)) {
                        damage *= 2;
                        System.out.print("Critical hit! ");
                    }
                    System.out.print("You dealt " + damage + " damage");
                    if(check) {
                        System.out.print(" (buffed attack)");
                    }
                    enemyHP -= damage;
                    System.out.println("\nEnemy HP: " + Math.max(0, enemyHP));

                } else {
                    System.out.println("Miss");
                }

                check = false;
                if (enemyHP <= 0) {
                    System.out.println("Enemy died in " + a + " turns");
                    scan.close();
                    return;
                }
            }

        }
    }
}
