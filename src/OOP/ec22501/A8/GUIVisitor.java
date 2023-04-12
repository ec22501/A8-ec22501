package OOP.ec22501.A8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.io.InputStream;
import java.util.Scanner;

class GUIVisitor implements Visitor {
    static enum Semaphore {WAITING, ACCEPT_ITEM, REJECT_ITEM};
    JFrame db = new JFrame();
    JTextArea tellText = new JTextArea("Hello!\n");
    JButton acceptButton = new JButton("Accept Item");
    Semaphore acceptSemaphore = Semaphore.WAITING;

         
    private PrintStream out;
    private Scanner in;
    private int purse;
    private Item[] items; 
    private int next;
    
    public GUIVisitor(PrintStream ps, InputStream is) {

        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                acceptSemaphore = Semaphore.ACCEPT_ITEM;

            }
        });

        db.getContentPane().add(tellText, BorderLayout.NORTH);
        db.getContentPane().add(acceptButton, BorderLayout.SOUTH);
        db.setTitle("GUIVisitor by ec22501");
        db.setSize(500,500);
        db.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        db.setVisible(true);


        out = ps;
        in = new Scanner(is);
        purse = 0;
        items = new Item[1000];
        next = 0;
    }
    
    
    private static final char[] yOrN = { 'y', 'n'};
   
    public void tell(String m) {
        tellText.append(m+"\n");
    }
    
    public char getChoice(String d, char[] a) {
        tell(d);
        if (!in.hasNextLine()) {
            tell("'No line' error");
            return '?';
        }
        String t = in.nextLine();
        if (t.length() > 0) 
            return t.charAt(0);
        else {
            if (a.length > 0) {
                tell("Returning "+a[0]);
                return a[0];
            } else {
                tell("Returning '?'");
                return '?';
            } 
        }
    }
    
    public boolean giveItem(Item x) {
        tell("You have: ");
        for (int i=0;i<next;i++) out.print(items[i] + ", ");
        tell("You are being offered: "+x.name);
        if (next >= items.length) {
            out.println("But you have no space and must decline.");
            return false;
        }

        // Want to wait for accept button press.
        acceptSemaphore = Semaphore.WAITING;
        while (acceptSemaphore == Semaphore.WAITING)

        if (acceptSemaphore == Semaphore.ACCEPT_ITEM) {
            items[next] = x;
            next++;
            return true;
        } else return false;
        return false;
    }
    
    public boolean hasIdenticalItem(Item x) {
        for (int i=0; i<next;i++) 
            if (x == items[i]) 
                return true;
        return false;
    }
        
    public boolean hasEqualItem(Item x) {
        for (int i=0; i<next;i++) 
            if (x.equals(items[i])) 
                return true;
        return false;
    }
    
    public void giveGold(int n) {
        tell("You are given "+n+" gold pieces.");
        purse += n;
        tell("You now have "+purse+" pieces of gold.");
    }
        
    public int takeGold(int n) {
        
        if (n<0) {
            tell("A scammer tried to put you in debt to the tune off "+(-n)+"pieces of gold,");
            tell("but you didn't fall for it and returned the 'loan'.");
            return 0;
        }
        
        int t = 0;
        if (n > purse) t = purse;
        else t = n;
        
        tell(t+" pieces of gold are taken from you.");
        purse -= t;
        tell("You now have "+purse+" pieces of gold.");
        
        return t;
    }
}
