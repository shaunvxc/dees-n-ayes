package structures;



public class MapTester {

	private static final String[] keys = new String[] { "one", "two", "three",
			"fah", "five", "six", "seven", "eight", "nine", "ten", "eleven",
			"twelve", "thahteen", "fahteen", "fifteen", "sixteen", "seventeen",
			"eighteen" };

	public static void main(String[] args) {

		VigMap<String, String> map = new VigMap<String, String>();
		/*
		Random generator = new Random();
		
		for (int i = 0; i < keys.length; i++) {
			String val = generator.nextInt(50) + "";
			System.out.println("Setting Key " + keys[i] + " with a value of: "
					+ val);
			map.put(keys[i], val);
		}

		System.out.println("Getting values out of Map: ");

		for (int i = 0; i < keys.length; i++) {
			System.out.println("Value mapped to Key: " + keys[i] + "= "
					+ map.get(keys[i]));
		}
		*/
		
		for(int i = 0; i < 1026; i++) {
			map.put(i + " ", i + "");
		}
		
		for(int i = 0; i < 1026; i++) {
			System.out.println("value for " + i + " = " + map.get(i + " "));
		} 
	}

}
