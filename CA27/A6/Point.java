class Point {
    float x, y;
    
    public Point (float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Point () {
        x = y = 0.0f;
    }

    public boolean isEqual(Point B) {
        return ((x == B.x) && (y == B.y));
    }

    public boolean isGreaterThan (Point P, Point ref) {
        // System.out.println("Points being compared: " + this + P);

        double u = P.x - ref.x, v = P.y - ref.y, p = x - ref.x, q = y - ref.y;

        double angle = Math.atan2(q, p), angleP = Math.atan2(v, u);
        // System.out.println("Angle of p1 wrt p0: " + angle);
        // System.out.println("Angle of p2 wrt p0: " + angleP);

        if ( angle > angleP)
            return true;
        else if (angle < angleP)
            return false;
        else 
            return (p * p + q * q) > (u * u + v * v);
        
    }

    public boolean isLE180(Point B, Point C) {
        return ((B.x - x) * (C.y - B.y) <= (C.x - B.x) * (B.y - y));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("(");
        String X = String.format("%.2f", x), Y = String.format("%5.2f", y);
        sb.append(X).append(",").append(Y).append(")");

        return sb.toString();
    }
}