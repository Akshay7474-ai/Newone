package exceptionhandling1;

public class Studentdet
{
        private int studentId;
        private String name;
        private int age;

        // Constructor
        public Studentdet(int studentId, String name) {
            this.studentId = studentId;
            this.name = name;
        }

        // age with validation
        public void setAge(int age) {
            if (age < 5 || age > 100) {
                throw new IllegalArgumentException("Age must be between 5 and 100.");
            }
            this.age = age;
        }

        // student details
        public void displayDetails() {
            System.out.println("Student ID: " + studentId);
            System.out.println("Name: " + name);
            System.out.println("Age: " + age);
            System.out.println("---------------------------");
        }

}



package exceptionhandling1;

public class StudentMain
{
        public static void main(String[] args)
        {
            // Create student instances
            Studentdet s1 = new Studentdet(001, "Ak");
            Studentdet s2 = new Studentdet(002, "Rana");

            // Try to set valid and invalid ages
            try {
                s1.setAge(21);
            } catch (IllegalArgumentException e) {
                System.out.println("Error setting age for Ak: " + e.getMessage());
            }

            try {
                s2.setAge(3); // Invalid age
            } catch (IllegalArgumentException e) {
                System.out.println("Error setting age for Rana: " + e.getMessage());
            }

            // Display details
            s1.displayDetails();
            s2.displayDetails();
        }

}
-------------------------------------------

2)package exh2;

public class BankAcount
{
        private String accountNumber;
        private double balance;

        // Constructor
        public BankAcount(String accountNumber) {
            this.accountNumber = accountNumber;
            this.balance = 0.0;
        }

        // Deposit method
        public void deposit(double amount) {
            if (amount <= 0) {
                System.out.println("Deposit amount must be positive.");
                return;
            }
            balance += amount;
            System.out.println("Deposited ₹" + amount + ". New balance: ₹" + balance);
        }

        // Withdraw method with exception handling
        public void withdraw(double amount) {
            try {
                if (amount <= 0) {
                    throw new IllegalArgumentException("Withdrawal amount must be a positive.");
                }
                if (amount > balance) {
                    throw new ArithmeticException("Insufficient balance..");
                }
                balance -= amount;
                System.out.println("Withdrew ₹" + amount + ". Remaining balance: ₹" + balance);
            } catch (IllegalArgumentException | ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        // Display account details
        public void displayAccount() {
            System.out.println("Account Number: " + accountNumber);
            System.out.println("Current Balance: ₹" + balance);
            System.out.println("-------------------------");
        }

}




package exh2;

public class BankDMain
{
        public static void main(String[] args) {
            BankAcount account = new BankAcount("SBI619");

            account.displayAccount();

            account.deposit(6000);
            account.withdraw(1900);
            account.withdraw(11000); // Should trigger with insufficient balance
            account.withdraw(-500);  // Should trigger invalid amount
            account.deposit(0);      // Should show warning
            account.deposit(4000);
            account.withdraw(2600);

            account.displayAccount();
        }

}

-------------------------------------------

3)package exh3;

public class MainPIM
{
        public static void main(String[] args) {
            // Create products
            ProductIM p1 = new ProductIM(201, "Headphone", 100);
            ProductIM p2 = new ProductIM(202, "Mobile", 20);
            ProductIM p3 = new ProductIM(203, "Mick", 30);

            // Valid and invalid quantity updates
            p1.updateQuantity(5);
            p2.updateQuantity(-10); // Should trigger exception
            p3.updateQuantity(15);

            // Display all products
            p1.displayProduct();
            p2.displayProduct();
            p3.displayProduct();
        }

}



package exh3;

public class ProductIM
{
        private int productId;
        private String productsName;
        private int quantity;

        // Constructor
        public ProductIM(int productId, String productsName, int quantity) {
            this.productId = productId;
            this.productsName = productsName;
            this.quantity = quantity;
        }

        // Method to update quantity
        public void updateQuantity(int amount) {
            try {
                if (amount < 0) {
                    throw new IllegalArgumentException("Quantity cannot be negative.");
                }
                quantity += amount;
                System.out.println("Updated quantity for " + productsName + ": " + quantity);
            } catch (IllegalArgumentException e) {
                System.out.println("Error updating " + productsName + ": " + e.getMessage());
            }
        }

        // Display product details
        public void displayProduct()
        {
            System.out.println("Product ID= " + productId);
            System.out.println("Products Name= " + productsName);
            System.out.println("Quantity= " + quantity);
            System.out.println("-----------------------");
        }

}
------------------------------------------

4)package exh4;

public class LBook
{
        private int bookId;
        private String title;
        private boolean isAvailable;

        // Constructor
        public LBook(int bookId, String title)
        {
            this.bookId = bookId;
            this.title = title;
            this.isAvailable = true; // default is available
        }

        // Method to rent a book
        public void rentBook() {
            try {
                if (!isAvailable) {
                    throw new IllegalStateException("Book is currently unavailable.");
                }
                isAvailable = false;
                System.out.println("Book rented: " + title);
            } catch (IllegalStateException e) {
                System.out.println("Error renting book '" + title + "': " + e.getMessage());
            }
        }

        // Method to return a book
        public void returnBook() {
            isAvailable = true;
            System.out.println("Book returned: " + title);
        }

        // Display book info
        public void displayStatus() {
            System.out.println("Book ID: " + bookId);
            System.out.println("Title : " + title);
            System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
            System.out.println("-------------------------");
        }

}



package exh4;

public class MainLBooksys
{
        public static void main(String[] args) {
            LBook b1 = new LBook(1, "Bhagawat Geeta");
            LBook b2 = new LBook(2, "1901");
            LBook b3 = new LBook(3, "World info");

            // First rental attempts
            b1.rentBook();
            b2.rentBook();

            // Try to rent an already rented book
            b1.rentBook();

            // Return and rent again
            b1.returnBook();
            b1.rentBook();

            // Display book status
            b1.displayStatus();
            b2.displayStatus();
            b3.displayStatus();
        }

}
--------------------------------------------

5)package exh5;

public class Calculator
{

        public double add(double a, double b) {
            return a + b;
        }

        public double subtract(double a, double b) {
            return a - b;
        }

        public double multiply(double a, double b) {
            return a * b;
        }

        public double divide(double a, double b) {
            try {
                if (b == 0) {
                    throw new ArithmeticException("Cannot divide by zero.");
                }
                return a / b;
            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
                return Double.NaN; // Representing an undefined result
            }
        }

}



package exh5;

public class MainCalcu
{
        public static void main(String[] args) {
            Calculator calc = new Calculator();

            double a = 25;
            double b = 7;
            double c = 0;

            System.out.println("Addition: " + calc.add(a, b));
            System.out.println("Subtraction: " + calc.subtract(a, b));
            System.out.println("Multiplication: " + calc.multiply(a, b));
            System.out.println("Division (valid): " + calc.divide(a, b));

            // This will trigger division by zero
            System.out.println("Division (by zero): " + calc.divide(a, c));
        }



}
------------------------------------