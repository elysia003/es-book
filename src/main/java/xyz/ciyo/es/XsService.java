package xyz.ciyo.es;

import org.springframework.data.elasticsearch.core.SearchHits;

import xyz.ciyo.entity.Xs;

public interface XsService {
	public boolean createOrUpdateIndex();
	public boolean indexExists();
	public boolean save(Xs recode);
	public int searchNum(String recode);
	public SearchHits<Xs> searchOnePageMatch(String recode, int pageindex, int pageSize);
	public SearchHits<Xs> searchOnePageTerm(String recode, int pageindex, int pageSize);
	public SearchHits<Xs> searchOnePagePhrase(String recode, int pageindex, int pageSize);
	public Xs getById(Integer id);
}
