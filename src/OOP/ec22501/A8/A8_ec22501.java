package OOP.ec22501.A8;

public class A8_ec22501 {
    public static void main(String[] args) {
        Visitable r = new Room_ec22501();
        Visitor adventurer = new GUIVisitor(System.out,System.in);
        r.visit(adventurer, Direction.FROM_SOUTH);
    }
}