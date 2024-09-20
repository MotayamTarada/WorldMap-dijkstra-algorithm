package application;


public class vertex implements Comparable<vertex>  {
	City city; 
	vertex previous;
	int num;
	double distance=Integer.MAX_VALUE; // positive 
	boolean visited;
	LinkedList<edges> e = new LinkedList<edges>();// add edged build graph 

	public vertex(City city, int number) {
		super();
		this.city = city;
		this.num = number;
	}

	public City getCity() {
		return city;
	}


	public void setCity(City city) {
		this.city = city;
	}


	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public LinkedList<edges> getE() {
		return e;
	}

	public void setE(LinkedList<edges> e) {
		this.e = e;
	}

//	public boolean FindEdge(String ss) {
//
//		for (int i = 0; i < e.size(); i++) {
//			if (e.get(i).getD().getCity().getName().compareToIgnoreCase(ss) == 0)
//				return true;
//		}
//		return false;
//	}

	public String ttoString() {
		String r = city.getName()+":";
		for (int i = 0; i < e.size(); i++) {
			r = r + e.get(i).desination.city.getName() + ",";
		}
		return r;
	}

	@Override
	public int compareTo(vertex o) {
	    return Double.compare(this.distance, o.distance);
	}



	
}
