package se.coredev.zoo;

import java.util.HashSet;
import java.util.Set;

import se.coredev.zoo.model.Animal;
import se.coredev.zoo.model.Dangerous;

public final class Zoo {

	private final Set<ZooListener> listeners;
	private final ZooStorage wildStorage;
	private final ZooStorage domesticStorage;

	public Zoo(ZooStorage wildStorage, ZooStorage domesticStorage) {
		this.wildStorage = wildStorage;
		this.domesticStorage = domesticStorage;
		this.listeners = new HashSet<>();
	}

	public void addAnimal(Animal animal) {

		if (animal instanceof Dangerous) {
			throw new ZooException("Can not add a dangerous animal");
		}

		if (animal.isDomestic()) {
			domesticStorage.addAnimal(animal);
		}
		else {
			wildStorage.addAnimal(animal);
		}
		notifyListeners(animal);
	}

	public void addListener(ZooListener listener) {
		listeners.add(listener);
	}

	public Animal getAnimal(String id) {
		Animal animal = domesticStorage.getById(id);
		
		if (animal == null) {
			return wildStorage.getById(id);
		}

		return animal;
	}

	private void notifyListeners(Animal animal) {

		for (ZooListener listener : listeners) {
			listener.animalAdded(animal);
		}
	}

}
