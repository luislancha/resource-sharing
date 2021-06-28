package galaxy.far.far.away.resourcessharing.repository;

import galaxy.far.far.away.resourcessharing.dto.InventarioDto;
import galaxy.far.far.away.resourcessharing.dto.RebeldeDto;
import galaxy.far.far.away.resourcessharing.model.RebeldeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static galaxy.far.far.away.resourcessharing.model.RebeldeModel.GET_INVENTARIO;

@Repository
public class RebeldeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public RebeldeDto save (final RebeldeDto rebeldeDto) {
        entityManager.persist(rebeldeDto);

        return rebeldeDto;
    }

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public RebeldeDto update (final RebeldeDto rebeldeDto) {
        entityManager.merge(rebeldeDto);

        return rebeldeDto;
    }

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public List<InventarioDto> findInventario (final String nome) {
        final RebeldeModel rebeldeModel = entityManager.find(RebeldeModel.class, nome);

        return new ArrayList<>();
    }
}
