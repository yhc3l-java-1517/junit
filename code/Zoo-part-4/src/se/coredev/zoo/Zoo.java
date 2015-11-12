package se.coredev.zoo;

import java.util.HashSet;
import java.util.Set;

import se.coredev.zoo.model.Animal;
import se.coredev.zoo.model.Dangerous;

public final class Zoo {

	private Set<Animal> animals;
	private Set<ZooListener> listeners;

	public Zoo() {
		animals = new HashSet<>();
		listeners = new HashSet<>();
	}

	public boolean addAnimal(Animal animal) {
		
		if (animal instanceof Dangerous) {
			throw new ZooException("Can not add a dangerous animal");
		}

		if (animals.add(animal)) {
			notifyListeners(animal);
			return true;
		}

		return false;
	}

	public void addListener(ZooListener listener) {
		listeners.add(listener);
	}

	private void notifyListeners(Animal animal) {

		for (ZooListener listener : listeners) {
			listener.animalAdded(animal);
		}
	}

}
