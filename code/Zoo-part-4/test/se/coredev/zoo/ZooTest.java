package se.coredev.zoo;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import se.coredev.zoo.model.Animal;
import se.coredev.zoo.model.Dog;
import se.coredev.zoo.model.Spider;

public class ZooTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	private Zoo zoo;

	@Before
	public void setup() {
		zoo = new Zoo();
	}
	
	@Test
	public void shouldNotAllowDangerousAnimal() {
		
		thrown.expect(ZooException.class);
		thrown.expectMessage("Can not add a dangerous animal");
		
		Animal spider = new Spider("1001", "Fido");
		zoo.addAnimal(spider);
	}

	@Test
	public void animalShouldBeInZooAfterAdd() {
		Animal dog = new Dog("1001", "Fido");

		boolean added = zoo.addAnimal(dog);
		assertTrue(added);		
	}

	@Test
	public void shouldNotAllowSameAnimal() {
		Animal dog1 = new Dog("1001", "Fido");
		Animal dog2 = new Dog("1001", "Fido");

		boolean added = zoo.addAnimal(dog1);
		assertTrue(added);

		added = zoo.addAnimal(dog2);
		assertFalse(added);
	}

	@Test
	public void listenersWillBeNotified() {
		
		AtomicBoolean added = new AtomicBoolean(false);
		Animal dog = new Dog("1001", "Fido");
		
		zoo.addListener(new ZooListener() {
			
			@Override
			public void animalAdded(Animal animal) {
				assertThat(animal, equalTo(dog));
				added.set(true);
			}
		});
		
		zoo.addAnimal(dog);
		assertTrue(added.get());
	}
	
}
