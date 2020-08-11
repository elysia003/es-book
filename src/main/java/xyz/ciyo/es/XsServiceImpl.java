package xyz.ciyo.es;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import xyz.ciyo.entity.Xs;

@Service
public class  XsServiceImpl implements XsService{
	@Autowired
	private ElasticsearchRestTemplate esTemplate;
	@Autowired
	private XsDao dao;
	
	/**
	 * 创建/更新索引
	 */
	public boolean createOrUpdateIndex() {
		esTemplate.createIndex(Xs.class);
		esTemplate.putMapping(Xs.class);
		return true;
	}

	/**
	 * 判断索引是否存在
	 *
	 * @return
	 */
	public boolean indexExists() {
		IndexOperations indexOperations = esTemplate.indexOps(Xs.class);
		return indexOperations.exists();
	}

	/**
	 * 保存
	 * @return 
	 *
	 * @return
	 */
	public boolean save(Xs recode) {
		if(esTemplate.save(recode)!=null) {
			return true;
		}
		return false;
	}
	/**
	 * 全文搜索 数量
	 *
	 * @return
	 */
	public int searchNum(String recode) {
		TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms("count").field("id");
		NativeSearchQuery build = new NativeSearchQueryBuilder()
				.withQuery(QueryBuilders.matchQuery("content", recode))
				.addAggregation(termsAggregationBuilder)
				.build();
		return (int) esTemplate.search(build, Xs.class).getTotalHits();
	}
	/**
	 * 全文搜索，返回一页
	 *
	 * @return
	 */
	public SearchHits<Xs> searchOnePageMatch(String recode, int pageindex, int pageSize) {
		String[] fields = {"hostIP","pathFile"};
		FetchSourceContext sourceContext = new FetchSourceContext(true,fields, null);
		// 高亮设置
		HighlightBuilder highlightBuilder = new HighlightBuilder();
		highlightBuilder.requireFieldMatch(true); // 如果该属性中有多个关键字 则都高亮
		highlightBuilder.field("content").field("title");
		highlightBuilder.preTags("<span style='color:red'>");
		highlightBuilder.postTags("</span>");
		
		// 分页条件
		pageindex = pageindex == 0 ? 1 : pageindex;
		pageSize = pageSize == 0 ? 10 : pageSize;
		Pageable pageable = PageRequest.of(pageindex - 1, pageSize);
		
		NativeSearchQuery build = new NativeSearchQueryBuilder()
				.withQuery(QueryBuilders.matchQuery("content", recode))
				.withHighlightBuilder(highlightBuilder)
				.withPageable(pageable)
				//评分降序
				.withSort(SortBuilders.scoreSort())
				.withFields(new String[]{"id","title","classify","auth"})
				.build();
		return esTemplate.search(build, Xs.class);
	}
	public Xs getById(Integer id) {
		return dao.findById(id).get();
	}

	@Override
	public SearchHits<Xs> searchOnePageTerm(String recode, int pageindex, int pageSize) {
		String[] fields = {"hostIP","pathFile"};
		FetchSourceContext sourceContext = new FetchSourceContext(true,fields, null);
		// 高亮设置
		HighlightBuilder highlightBuilder = new HighlightBuilder();
		highlightBuilder.requireFieldMatch(true); // 如果该属性中有多个关键字 则都高亮
		highlightBuilder.field("content").field("title");
		highlightBuilder.preTags("<span style='color:red'>");
		highlightBuilder.postTags("</span>");
		
		// 分页条件
		pageindex = pageindex == 0 ? 1 : pageindex;
		pageSize = pageSize == 0 ? 10 : pageSize;
		Pageable pageable = PageRequest.of(pageindex - 1, pageSize);
		
		NativeSearchQuery build = new NativeSearchQueryBuilder()
				.withQuery(QueryBuilders.termQuery("content", recode))
				.withHighlightBuilder(highlightBuilder)
				.withPageable(pageable)
				//评分降序
				.withSort(SortBuilders.scoreSort())
				.withFields(new String[]{"id","title","classify","auth"})
				.build();
		return esTemplate.search(build, Xs.class);
	}
	@Override
	public SearchHits<Xs> searchOnePagePhrase(String recode, int pageindex, int pageSize) {
		String[] fields = {"hostIP","pathFile"};
		FetchSourceContext sourceContext = new FetchSourceContext(true,fields, null);
		// 高亮设置
		HighlightBuilder highlightBuilder = new HighlightBuilder();
		highlightBuilder.requireFieldMatch(true); // 如果该属性中有多个关键字 则都高亮
		highlightBuilder.field("content").field("title");
		highlightBuilder.preTags("<span style='color:red'>");
		highlightBuilder.postTags("</span>");
		
		// 分页条件
		pageindex = pageindex == 0 ? 1 : pageindex;
		pageSize = pageSize == 0 ? 10 : pageSize;
		Pageable pageable = PageRequest.of(pageindex - 1, pageSize);
		
		NativeSearchQuery build = new NativeSearchQueryBuilder()
				.withQuery(QueryBuilders.matchPhraseQuery("content", recode))
				.withHighlightBuilder(highlightBuilder)
				.withPageable(pageable)
				//评分降序
				.withSort(SortBuilders.scoreSort())
				.withFields(new String[]{"id","title","classify","auth"})
				.build();
		return esTemplate.search(build, Xs.class);
	}
}