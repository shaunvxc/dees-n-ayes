package problems;
/**
 * class to solve the i18nStrings problem
 * 
 */
public class I18NStrings {
	
	
	public static void printI18NStrings(String s) {
		int n = s.length() - 1;
		
		
		while(n > 0) {
			genStrings(s, n);
			n--;
		}
	}

	private static void genStrings(String s, int n) {
		
		int idx = 1;
		String prefix = null;
		String suffix = null;
		
		while(idx + n < s.length() ) {
			prefix = s.substring(0, idx);
			suffix = s.substring(idx +n);
			System.out.println(prefix + n + suffix);
			idx++;
		}
	}
	


    public static void getPerms(String input) {
        if (input.contains("?")) {
            getPerms(input.replaceFirst("\\?", "0"));
            getPerms(input.replaceFirst("\\?", "1"));
        }
        else {
            System.out.println(input);
        }
    }

    
	
	public static void main(String[] args) {
		//printI18NStrings("VIGUERIE");
	//	printI18NStrings("CAREERCUP");
	
		getPerms("011?01?1?");
	}
	
}
