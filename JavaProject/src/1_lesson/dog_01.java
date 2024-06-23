class Dog {
    int size;
    String name;

    void bark(int numOfBarks) {
        while (numOfBarks > 0) {
           if (size > 60) {
            System.out.println("Bark! Bark!");
            }
            else if (size > 14) {
                System.out.println("bark bark");
            }
            else {
                System.out.println("fiu fiu");
            } 
            numOfBarks -= 1;
        }
    }
}  

class DogTestDrive{
    public static void main(String[] args) {
        Dog one = new Dog();
        one.size = 70;

        Dog two = new Dog();
        two.size = 8;

        Dog three = new Dog();
        three.size = 35;

        one.bark( 3);
        two.bark( 2);
        three.bark( 5);
    }
}