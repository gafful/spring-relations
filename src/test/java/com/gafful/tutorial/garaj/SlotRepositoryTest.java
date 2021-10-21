package com.gafful.tutorial.garaj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SlotRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SlotRepository slotRepository;

    @Test
    public void givenSlots_whenFetchSlots_thenReturnAllSlots() {
        // given slot without vehicle
        Slot slotBo1 = new Slot("A1");

        // given slot with vehicle
        Vehicle vehicleBo2 = new Vehicle("A 10");
        Slot slotBo2 = new Slot("A2", vehicleBo2);

        entityManager.persist(slotBo1);
        entityManager.persist(vehicleBo2);
        entityManager.persist(slotBo2);
        entityManager.flush();

        // when fetch all slots
        List<Slot> slots = slotRepository.findAll();

        // then return all slots
        assertThat(slots.size())
                .isEqualTo(2);
        assertThat(slots.get(0).getName())
                .isEqualTo(slotBo1.getName());
        assertThat(slots.get(1).getName())
                .isEqualTo(slotBo2.getName());
    }

}
