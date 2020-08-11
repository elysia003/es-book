package xyz.ciyo.es;

import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import xyz.ciyo.entity.Xs;

public interface XsDao extends ElasticsearchRepository<Xs,Integer>{
	Optional<Xs> findById(Integer id);
}