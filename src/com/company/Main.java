package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
	// write your code here
        String currentUserEmail;
        Scanner sc =new Scanner(System.in);
        System.out.println("Choose An option\n1) for login\n2) for signup");
        int choice = sc.nextInt();
        Authentication auth=new Authentication();
        sc.nextLine();

        while(true) {
            if (choice == 1) {
                System.out.println("Enter Email Address");
                String email = sc.nextLine();
                System.out.println("Enter Password");
                String password = sc.nextLine();
                int response = auth.login(email, password);
                if (response == 1) {
                    System.out.println("You have logged in successfully");
                    currentUserEmail=email;
                    break;
                } else {
                    System.out.println("Incorrect username or Password\n Try Again\n\n");

                }
            } else {
                System.out.println("Enter first name");
                String firstname = sc.nextLine();
                System.out.println("Enter last name");
                String lastname = sc.nextLine();
                System.out.println("Enter Email Address");
                String email = sc.nextLine();
                System.out.println("Enter Password");
                String password = sc.nextLine();
                int response = auth.signUp(firstname, lastname, email, password);
                if (response == 1) {
                    System.out.println("Registration successful \n\n");
                    System.out.println("enter details to login ");
                    choice=1;

                } else {
                    System.out.println("Some Error occurred : try again ");
                }
            }
        }
        CartProcess cp=new CartProcess();
        while(true)
        {
            System.out.println("Choose an option\n1) view Cars in Store\n2) view Cart Items with Details\n3) add " +
                    "items to cart\n4) delete an item from cart\n5) clear cart \n6) exit");
            try{
                choice = sc.nextInt();
            }catch (Exception e)
            {
                continue;
            }
            if(choice==6)
            {
                System.out.println("Shutting down the program");
                break;
            }
            sc.nextLine();
            switch (choice)
            {
                case 1:
                    cp.showCars();
                    System.out.println("\n\n");
                    break;
                case 2:
                    cp.showCart(currentUserEmail);
                    System.out.println("\n\n");
                    break;
                case 3:
                    System.out.println("Enter Car_id from table to add to cart");
                    String car_id=sc.nextLine();
                    cp.addToCart(car_id,currentUserEmail);
                    System.out.println("\n\n");
                    break;
                case 4:
                    System.out.println("Enter Car_id from table to delete from cart");
                    String id=sc.nextLine();
                    cp.deleteFromCart(id,currentUserEmail);
                    System.out.println("\n\n");
                    break;
                case 5:
                    cp.clearCart(currentUserEmail);
                    System.out.println("\n\n");
                    break;

            }
        }


    }
}
