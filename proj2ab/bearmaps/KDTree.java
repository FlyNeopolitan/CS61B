package bearmaps;

import java.util.List;

public class KDTree {
    Node root = null;

    //constructor of KDTree
    //root's child is divided by x's position, and if you want root's child divided by y's position, just change add method to add(i, root, 0);
    public KDTree(List<Point> points) {
        for (Point i : points) {
            root = add(i, root, 1);
        }
    }

    // add Point x to KDTree T (is a helper method for constructor)
    private Node add(Point x, Node T, int isx) {
        if (T == null) {
            return new Node(x, isx, null, null);
        }
        if (T.compareTo(x) > 0) {
            T.leftChild = add(x, T.leftChild, 1 - isx);
        }
        if (T.compareTo(x) <= 0) {
            T.rightChild = add(x, T.rightChild, 1 - isx);
        }
        return T;
    }

    //return Point in KDTree nearest to Point(x, y)
    public Point nearest(double x, double y) {
        Point target = new Point(x, y);
        return nearestHelper(target, root.point, root);
    }

    //helper method for nearest method
    private Point nearestHelper(Point target, Point best, Node T) {
        if (T == null) {
            return best;
        }
        if (Point.distance(T.point, target) < Point.distance(best, target)) {
            best = T.point;
        }
        Node goodSide, badSide;
        if (T.compareTo(target) > 0) {
            goodSide = T.leftChild;
            badSide = T.rightChild;
        } else {
            goodSide = T.rightChild;
            badSide = T.leftChild;
        }
        best = nearestHelper(target, best, goodSide);
        if (Double.compare(T.shortestDis(target), Point.distance(best, target)) < 0) {
            best = nearestHelper(target, best, badSide);
        }
        return best;
    }

    //node in KDTree
    // if (isX > 0), child is divided by x's position, and if not, child is divided by y's position.
    private class Node {
        private Point point;
        private int isX;
        private Node leftChild;
        private Node rightChild;

        public Node(Point p, int isx, Node left, Node right) {
            isX = isx;
            point = new Point(p.getX(), p.getY());
            leftChild = left;
            rightChild = right;
        }

        public int compareTo(Point o){
            if (isX > 0) {
                return Double.compare(point.getX(), o.getX());
            } else {
                return Double.compare(point.getY(), o.getY());
            }
        }

        public double shortestDis(Point o) {
            if (isX > 0) {
                return Math.pow(o.getX() - point.getX(), 2);
            } else {
                return Math.pow(o.getY() - point.getY(), 2);
            }
        }

        public int getIsX() {
            return isX;
        }
    }


}
