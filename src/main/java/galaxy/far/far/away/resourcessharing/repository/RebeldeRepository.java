package galaxy.far.far.away.resourcessharing.repository;

import galaxy.far.far.away.resourcessharing.dto.LocalizacaoDto;
import galaxy.far.far.away.resourcessharing.dto.RebeldeDto;
import galaxy.far.far.away.resourcessharing.exception.RebeldeNaoEncontradoException;
import galaxy.far.far.away.resourcessharing.mapper.LocalizacaoMapper;
import galaxy.far.far.away.resourcessharing.mapper.RebeldeMapper;
import galaxy.far.far.away.resourcessharing.model.LocalizacaoModel;
import galaxy.far.far.away.resourcessharing.model.RebeldeModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static galaxy.far.far.away.resourcessharing.model.RebeldeModel.*;

@Repository
public class RebeldeRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public RebeldeDto save (final RebeldeDto rebeldeDto) {

        RebeldeModel rebeldeModel = RebeldeMapper.INSTANCE.mapFrom(rebeldeDto);
        entityManager.persist(rebeldeModel);

        return RebeldeMapper.INSTANCE.mapFrom(rebeldeModel);
    }

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public LocalizacaoDto save (final LocalizacaoDto localizacaoDto) {
        LocalizacaoModel localizacaoModel = LocalizacaoMapper.INSTANCE.mapFrom(localizacaoDto);
        entityManager.persist(localizacaoModel);
        return LocalizacaoMapper.INSTANCE.mapFrom(localizacaoModel);
    }

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public RebeldeDto update (final RebeldeDto rebeldeDto) {
        RebeldeModel rebeldeModel = RebeldeMapper.INSTANCE.mapFrom(rebeldeDto);
        entityManager.merge(rebeldeModel);

        return RebeldeMapper.INSTANCE.mapFrom(rebeldeModel);
    }

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public RebeldeDto findRebelde (final String nome) {
//        final RebeldeModel rebeldeModel = Optional.ofNullable(entityManager.find(RebeldeModel.class, nome)).orElseThrow(RebeldeNaoEncontradoException::new);
        try {
            final RebeldeModel rebeldeModel = Optional.ofNullable(entityManager.createNamedQuery(FIND_REBELDE, RebeldeModel.class)
                    .setParameter("nome", nome)
                    .getSingleResult())
                    .orElseThrow(RebeldeNaoEncontradoException::new);

            return RebeldeMapper.INSTANCE.mapFrom(rebeldeModel);
        } catch (NoResultException e) {
            throw new RebeldeNaoEncontradoException();
        }
    }

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public boolean localizacaoExists (final String nome) {
        return Optional.ofNullable(entityManager.find(LocalizacaoModel.class, nome)).isPresent();
    }

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public List<RebeldeDto> findAll () {
        final List<RebeldeModel> rebeldeModelList= entityManager.createNamedQuery(FIND_ALL, RebeldeModel.class).getResultList();

        return RebeldeMapper.INSTANCE.toList(rebeldeModelList);
    }

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public List<RebeldeDto> findAllRebeldes () {
        final List<RebeldeModel> rebeldeModelList= entityManager.createNamedQuery(FIND_ALL_REBELDES, RebeldeModel.class).getResultList();

        return RebeldeMapper.INSTANCE.toList(rebeldeModelList);
    }

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public List<RebeldeDto> findAllTraidores () {
        final List<RebeldeModel> rebeldeModelList= entityManager.createNamedQuery(FIND_ALL_TRAIDORES, RebeldeModel.class).getResultList();

        return RebeldeMapper.INSTANCE.toList(rebeldeModelList);
    }
}
