package se.coredev.zoo.model;

import java.util.UUID;

public abstract class Animal {

	private final String id;
	private final String name;
	private final boolean domestic;

	protected Animal(String name, boolean domestic) {
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.domestic = domestic;
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public boolean isDomestic() {
		return domestic;
	}

	public boolean isWild() {
		return !domestic;
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
