package se.coredev.zoo.model;

public abstract class Animal {

	private String id;
	private String name;

	protected Animal(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}

		if (other instanceof Animal) {
			Animal otherAnimal = (Animal) other;
			return id.equals(otherAnimal.id) && name.equals(otherAnimal.name) &&
			        getClass().equals(other.getClass());
		}

		return false;
	}

	@Override
	public int hashCode() {
		int result = 1;
		result += 37 * id.hashCode();
		result += 37 * name.hashCode();
		return result;
	}

}
