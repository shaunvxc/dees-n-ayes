package structures.graph;

public class Vertex implements Comparable<Vertex>
{
    public final int name;
    public double minDistance = Double.POSITIVE_INFINITY;
    public Vertex previous;
    
    public Vertex(int argName) { 
    	name = argName;
    }
    public Vertex(int argName, int minDistance) { 
    	name = argName;
    	this.minDistance = minDistance;
    }
    
    public int compareTo(Vertex other)
    {
        return Double.compare(minDistance, other.minDistance);
    }
}
