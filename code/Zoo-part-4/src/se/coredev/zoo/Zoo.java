package se.coredev.zoo;

import java.util.HashSet;
import java.util.Set;

import se.coredev.zoo.model.Animal;
import se.coredev.zoo.model.Dangerous;

public final class Zoo {

	private Set<Animal> animals;

	public Zoo() {
		animals = new HashSet<>();
	}

	public boolean addAnimal(Animal animal) {
		if (animal instanceof Dangerous) {
			throw new ZooException("Can not add a dangerous animal");
		}
		return animals.add(animal);
	}

}
