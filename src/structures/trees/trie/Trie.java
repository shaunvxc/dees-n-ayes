package structures.trees.trie;

public class Trie {
	
		private TrieNode root;
		
		public Trie() {
			root = new TrieNode(' ');
		}
		
		public void insert(String word) {
			if(search(word)) {
				return;
			}
			
			TrieNode current = root;
			for(char ch : word.toCharArray()) {
				TrieNode child = current.subNode(ch);
				if(child != null) {
					current = child;
				}
				else {
					current.childList.add(new TrieNode(ch));
					current = current.subNode(ch);
				}
				current.count++;
			}
			
			current.isEnd = true;
		}

		public boolean search(String word) {

			TrieNode current = root;	
			
			for(char ch : word.toCharArray()) {
				if(current.subNode(ch) == null) {
					return false;
				} else {
					current = current.subNode(ch);
				}
			}
			
			if(current.isEnd) {
				return true;
			}
			
			return false;
		}
		
		public void remove(String word) {
			if(!search(word)) {
				System.out.println(word + " is not present in Trie");
				return ;
			}
			
			TrieNode current = root;
			for(char ch : word.toCharArray()) {
				TrieNode child = current.subNode(ch);
				if(child.count ==1) {
					current.childList.remove(child);
					return;
				}
				else {
					child.count--;
					current = child;
				}
			}
			
			current.isEnd = false;
		}
		
}
