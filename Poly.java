/**
 * Class to represent a polynomial, e.g. 3.5x^4 + 3x^2 - 4.
 * 
 * Polynomials can be added, subtracted, multiplied, and divided.
 * 
 * This class is a skeleton. You need to provide implementations
 * for the methods here defined. Feel free however, to add additional
 * methods as you see fit.
 *
 * @author Adam Gulick
 * @version 2.3
 */
public class Poly{
    private Node first = new Node(0, 0);
    private Node last  = first;
    //private int deg = this.first.next.exp;

    private static class Node {
        double coef;
        int exp;
        Node next;
        Node(double coef, int exp) {
            this.coef = coef;
            this.exp  = exp;
        }
    }

    /**
     * Creates a new polynomial containing a single term with the coefficient
     * and exponent passed in as arguments. E.g. when called with coefficient
     * 3.5 and exponent 2, it should create a polynomial 3.5x^2.
     * 
     * You can create additional constructors if you'd like, but it's 
     * imperative that this one exists.
     * 
     * @param coef the single term's coefficient.
     * @param exp the single term's exponent.
     * @return the polynomial created.
     */
    public Poly(double coef, int exp){
        last.next = new Node(coef, exp);
        last = last.next;
    }

    private Poly() { }

    /**
     * Adds the polynomial passed in as an argument, p, to the polynomial on which the 
     * method is called on (the "this" polynomial), and returns a new polynomial
     * with the result. I.e., it returns "this + p".
     * 
     * @param p the polynomial to add onto the polynomial on which the method is called on.
     * @return a polynomial representing the result of the addition.
     */
    public Poly add(Poly p){
        Poly a = this;
        Poly c = new Poly();

        Node x = a.first.next;
        Node y = p.first.next;
        while (x != null || y != null) {
            Node t = null;
            if      (x == null)     {
                t = new Node(y.coef, y.exp);
                y = y.next; }
            else if (y == null)     {
                t = new Node(x.coef, x.exp);
                x = x.next; }
            else if (x.exp > y.exp) {
                t = new Node(x.coef, x.exp);
                x = x.next; } 
            else if (x.exp < y.exp) {
                t = new Node(y.coef, y.exp);
                y = y.next; } 

            else {
                double coef = x.coef + y.coef;
                int exp  = x.exp;
                x = x.next;
                y = y.next;
                if (coef == 0)
                    continue;
                t = new Node(coef, exp);
            }

            c.last.next = t;
            c.last = c.last.next;
        }
        return c;
    }

    /**
     * Subtracts the polynomial passed in as an argument, p, from the polynomial on which the 
     * method is called on (the "this" polynomial), and returns a new polynomial
     * with the result. I.e., it returns "this - p".
     * 
     * @param p the polynomial to be subtracted from the polynomial on which the method is called on.
     * @return a polynomial representing the result of the subtraction.
     */
    public Poly subtract(Poly p){
        Poly a = this;
        Poly c = new Poly();
        Node x = a.first.next;
        Node y = p.first.next;
        while (x != null || y != null) {
            Node t = null;
            if      (x == null)     {
                t = new Node(-y.coef, y.exp);
                y = y.next; }
            else if (y == null)     {
                t = new Node(x.coef, x.exp);
                x = x.next; }
            else if (x.exp > y.exp) {
                t = new Node(x.coef, x.exp);
                x = x.next; } 
            else if (x.exp < y.exp) {
                t = new Node(-y.coef, y.exp);
                y = y.next; } 

            else {
                double coef = x.coef - y.coef;
                int exp  = x.exp;
                x = x.next;
                y = y.next;
                if (coef == 0)
                    continue;
                t = new Node(coef, exp);
            }

            c.last.next = t;
            c.last = c.last.next;
        }
        return c;
    }

    /**
     * Multiplies the polynomial passed in as an argument, p, by the polynomial on which the 
     * method is called on (the "this" polynomial), and returns a new polynomial
     * with the result. I.e., it returns "this * p".
     * 
     * @param p the polynomial to be multiplied by the polynomial on which the method is called on.
     * @return a polynomial representing the result of the multiplication.
     */
    public Poly multiply(Poly p){
        Poly a = this;
        Poly c = new Poly();
        for (Node x = a.first.next; x!= null; x = x.next) {
            Poly temp = new Poly();
            for (Node y = p.first.next; y!= null; y = y.next) {
                temp.last.next = new Node(x.coef * y.coef, x.exp + y.exp);
                temp.last = temp.last.next;
            }
            c = c.add(temp);
        }
        return c;
    }

    /**
     * Divides the polynomial on which the method is called on (the "this" polynomial), by
     * the polynomial passed in as an argument, p, and returns a new polynomial
     * with the resulting quotient. I.e., it returns "this / p".
     * 
     * The division should be performed according to the polynomial long division algorithm
     * ( https://en.wikipedia.org/wiki/Polynomial_long_division ).
     * 
     * Polynomial division may end with a non-zero remainder, which means the polynomials are
     * indivisible. In this case the method should return null. A division by zero should also
     * yield a null return value.
     * 
     * @param p the polynomial to be multiplied by the polynomial on which the method is called on.
     * @return a polynomial representing the quotient of the division, or null if they're indivisible.
     */    
    public Poly divide(Poly p){
        if ((p.toString().equals("0.0")) || (p==null)) return null;
        if (p.equals(new Poly(1.0, 0))) return this; 
        Poly q = new Poly();
        Poly r = this;
        while(!(r.equals(new Poly(0,0)))&&(r.first.next.exp >= p.first.next.exp)){
            double co = r.first.next.coef / p.first.next.coef;
            int ex = r.first.next.exp - p.first.next.exp;
            Poly t = new Poly(co, ex);
            q = q.add(t);
            Poly m = t.multiply(p);
            r = r.subtract(m);

        }
        if(r.equals(new Poly (0,0)))
            return q;
        return null;

    }

    /**
     * Compares the polynomial on which the method is called (the "this" polynomial), 
     * to the object passed in as argument, o. o is to be considered equal to the "this"
     * polynomial if they both represent equivalent polynomials.
     * 
     * E.g., "3.0x^4 + 0.0x^2 + 5.0" and "3.0x^4 + 5.0" should be considered equivalent.
     * "3.0x^4 + 5.0" and "3.0x^4 + 3.0" should not.
     * 
     * @param o the object to be compared against the polynomial the method is called on.
     * @return true if o is a polynomial equivalent to the one the method was called on,
     * and false otherwise.
     */
    public boolean equals(Object o){
        return this.toString().equals(o.toString());
    }

    /**
     * Returns a textual representation of the polynomial the method is called on.
     * The textual representation should be a sum of monomials, with each monomial 
     * being defined by a double coefficient, the letters "x^", and an integer exponent.
     * Exceptions to this rule: coefficients of 1.0 should be omitted, as should "^1",
     * and "x^0".
     * 
     * Terms should be listed in decreasing-exponent order. Terms with zero-coefficient
     * should be omitted. Each exponent can only appear once. 
     * 
     * Rules for separating terms, applicable to all terms other that the largest exponent one:
     *   - Terms with positive coefficients should be preceeded by " + ".
     *   - Terms with negative coefficients should be preceeded by " - ".
     * 
     * Rules for the highest exponent term (i.e., the first):
     *   - If the coefficient is negative it should be preceeded by "-". E.g. "-3.0x^5".
     *   - If the coefficient is positive it should not preceeded by anything. E.g. "3.0x^5".
     * 
     * The zero/empty polynomial should be represented as "0.0".
     * 
     * Examples of valid representations: 
     *   - "2.0x^2 + 3.0"
     *   - "3.5x + 3.0"
     *   - "4.0"
     *   - "-2.0x"
     *   - "4.0x - 3.0"
     *   - "0.0"
     *   
     * Examples of invalid representations: 
     *   - "+2.0x^2+3.0x^0"
     *   - "3.5x -3.0"
     *   - "- 4.0 + x"
     *   - "-4.0 + x^7"
     *   - ""
     * 
     * @return a textual representation of the polynomial the method was called on.
     */
    public String toString(){
        String s = "";
        if(first.next == null)
            return "0.0";
        for (Node curr = first.next; curr != null; curr = curr.next) {
            if (curr == first.next){
                if (curr.coef == 0)
                    s+= "0.0";
                else if(curr.exp == 0)
                    s+= curr.coef; 
                else if((curr.exp == 1)&&(curr.coef != 1))
                    s+= curr.coef  + "x";
                else if (curr.exp == 1)
                    s+="x";
                else if (curr.coef == 1)
                    s+= "x^" + curr.exp;
                else if (curr.coef == -1)
                    s+= "-x^" + curr.exp; 
                else
                    s+= curr.coef  + "x^" + curr.exp;
            }else if (curr.coef == 0){
                s+= "";
            }else if (curr.coef > 0){
                if(curr.exp == 0)
                    s+= " + " + curr.coef; 
                else if((curr.exp == 1)&&(curr.coef != 1))
                    s+= " + " + curr.coef  + "x";
                else if (curr.exp == 1)
                    s+= " + x";
                else if (curr.coef == 1)
                    s+= " + x^" + curr.exp;
                else
                    s+= " + " + curr.coef  + "x^" + curr.exp;
            }else if (curr.coef < 0){
                if(curr.exp == 0)
                    s+= " - " + (-curr.coef); 
                else if((curr.exp == 1)&&(curr.coef != -1))
                    s+= " - " + (-curr.coef)  + "x";
                else if (curr.exp == 1)
                    s+= " - x";
                else if (curr.coef == -1)
                    s+= " - x^" + (curr.exp);
                else
                    s+= " - " + (-curr.coef)  + "x^" + curr.exp;}
        }
        return s;

    }
}
