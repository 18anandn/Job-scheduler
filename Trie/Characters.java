package Trie;

public class Characters implements Comparable<Characters>{
	char character;
	Characters(char x){
		character = x;
	}

	public char character() {
		return character;
	}

	@Override
	public int hashCode() {
		int result = 0;
		result = character - 32;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Characters other = (Characters) obj;
		if (character != other.character)
			return false;
		return true;
	}

	@Override
	public int compareTo(Characters c) {
		if(character < c.character()) {
			return -1;
		}
		else if(character > c.character()) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public String toString() {
		return String.valueOf(character);
	}
}