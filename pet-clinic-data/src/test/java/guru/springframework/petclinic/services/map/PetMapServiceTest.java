package guru.springframework.petclinic.services.map;

import guru.springframework.petclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PetMapServiceTest {

    PetMapService petMapService;

    private final Long petId = 1L;

    @BeforeEach
    void setUp() {

        petMapService = new PetMapService();

        petMapService.save(Pet.builder().id(petId).build());
    }

    @Test
    void findAll() {

        Set<Pet> pets = petMapService.findAll();

        assertEquals(1, pets.size());
    }

    @Test
    void findByIdExistingId() {

        Pet pet = petMapService.findById(petId);

        assertEquals(petId, pet.getId());
    }

    @Test
    void findByIdNotExistingId() {

        Pet pet = petMapService.findById(5L);

        assertNull(pet);
    }

    @Test
    void save() {

        Long id = 2L;

        Pet pet = Pet.builder().id(id).build();

        Pet savedPet = petMapService.save(pet);

        assertEquals(id, pet.getId());
    }

    @Test
    void delete() {

        petMapService.delete(petMapService.findById(petId));

        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void deleteById() {

        petMapService.deleteById(petId);

        assertEquals(0, petMapService.findAll().size());
    }
}