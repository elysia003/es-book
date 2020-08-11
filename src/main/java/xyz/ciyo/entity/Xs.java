package xyz.ciyo.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.Mapping;
@Document(indexName = "xs-es",shards = 5,replicas = 1)
@Mapping(mappingPath="es-product.json")
public class Xs {
	@Id
    private Long id;
	@Field(name="title")
    private String title;
	@Field(name="content")
	private String content;
	@Field(name="classify")
	private String classify;
	@Field(name="auth")
    private String auth;
	@Field(name="tag")
    private String tag;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getClassify() {
		return classify;
	}
	public void setClassify(String classify) {
		this.classify = classify;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	@Override
	public String toString() {
		return "Xs [id=" + id + ", title=" + title + ", content=" + content + ", classify=" + classify + ", auth="
				+ auth + ", tag=" + tag + "]";
	}
}