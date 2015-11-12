package se.coredev.zoo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import se.coredev.zoo.model.Animal;

public final class ZooKeeper implements ZooListener {

	private Map<String, Animal> animals;

	public ZooKeeper() {
		animals = new HashMap<>();
	}

	@Override
	public void animalAdded(Animal animal) {

	}

//	public boolean hasAnimal(String animalId){
//		return animals.containsKey(animalId);
//	}
//	
//	public Set<Animal> getAnimals() {
//		return new HashSet<>(animals.values());
//	}

}
