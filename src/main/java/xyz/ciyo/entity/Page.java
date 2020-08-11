package xyz.ciyo.entity;

import java.util.List;

import org.springframework.data.elasticsearch.core.SearchHit;

public class Page {
	private String key;
	private int pageSum;
	private int nowIndex;
	private int pageSize;
	private int count;
	private double runtime;
	private String mode;
	private List<SearchHit<Xs>> list;
	
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getRuntime() {
		return runtime;
	}
	public void setRuntime(double runtime) {
		this.runtime = runtime;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getPageSum() {
		return pageSum;
	}
	public void setPageSum(int pageSum) {
		this.pageSum = pageSum;
	}
	public int getNowIndex() {
		return nowIndex;
	}
	public void setNowIndex(int nowIndex) {
		this.nowIndex = nowIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<SearchHit<Xs>> getList() {
		return list;
	}
	public void setList(List<SearchHit<Xs>> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "Page [key=" + key + ", count=" + pageSum + ", nowIndex=" + nowIndex + ", pageSize=" + pageSize + ", list="
				+ list + "]";
	}
	public Page(String key, int pageSum, int nowIndex, int pageSize, List<SearchHit<Xs>> list,int count,double runtime,String mode) {
		super();
		this.key = key;
		this.pageSum = pageSum;
		this.nowIndex = nowIndex;
		this.pageSize = pageSize;
		this.list = list;
		this.runtime=runtime;
		this.count=count;
		this.mode=mode;
	}
}
