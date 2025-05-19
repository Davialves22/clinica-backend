package com.mballem.curso.security.Repository;

import com.mballem.curso.security.model.Medico;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MedicoRepositoryTest {

    @Autowired
    Repository repository;

    @Test
    void salvarMedicoTest() {
        Medico medico = new Medico();
        medico.setNome("Dra. Ana Paula");
        medico.setEmail("ana.paula@medico.com");
        medico.setCrm("123456");
        medico.setTelefone("11999998888");

        repository.save(medico);
    }

    @Test
    void buscarMedicoPorIdTest() {
        Long id = 1L;
        Medico medico = repository.findById(id).orElse(null);
        if (medico != null) {
            System.out.println("Médico: " + medico.getNome());
        }
    }

    @Test
    void deletarMedicoTest() {
        Long id = 1L;
        repository.deleteById(id);
    }
}
