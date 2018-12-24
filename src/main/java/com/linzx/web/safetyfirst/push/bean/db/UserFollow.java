package com.linzx.web.safetyfirst.push.bean.db;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.UpdateTimestamp;
import org.jvnet.hk2.annotations.Optional;

/**
 * 用户关系的model 用于用户之间好友关系的实现
 * 
 * @author linzx 2018年12月24日
 */
@Entity
@Table(name = "TB_USER_FOLLOW")
public class UserFollow {

	// 这是一个主键
	@Id
	@PrimaryKeyJoinColumn
	// 主键生成存储的类型为uuid
	@GeneratedValue(generator = "uuid")
	// 把uuid的生成器定义为uuid2，uuid2是常规的uuid tostring
	@GenericGenerator(name = "uuid", strategy = "uuis2")
	// 不允许更改，不允许为null
	@Column(updatable = false, nullable = false)
	private String id;

	// 定义一个发起人，你关注某人，你就是发起人
	// 多对一 你可以创建很多个关注的信息
	// 多对一指：User对应多个UserFollow
	// optional不可选，必须存储，一条关注记录一定要有一个关注人
	@ManyToOne(optional = false)
	//定义关联的表的字段名为originId，对应的是User表的id
	//定义的是数据库中的存储字段
	@JoinColumn(name="originId")
	private User origin;
	//把这个列提取到我们的model中，不允许为null，不允许更新插入
	@Column(nullable=false,updatable=false,insertable=false)
	private String originId;

	// 定义关注目标，你关注的人
	// 也是多对一，你可以被很多人关注，每次关注都是一条记录
	// 多个UserFollow对应一个User
	@ManyToOne(optional = false)
	//定义关联的表的字段名为targetId，对应的是User表的id
	//定义的是数据库中的存储字段
	@JoinColumn(name="targetId")
	private User target;
	//把这个列提取到我们的model中，不允许为null，不允许更新插入
	@Column(nullable=false,updatable=false,insertable=false)
	private String targetId;

	// 别名，target的备注，可以为null
	@Column
	private String alias;

	// 定义为创建时间戳，在创建时就已经写入
	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime createAt = LocalDateTime.now();

	// 定义为更新时间戳，在创建时就已经写入
	@UpdateTimestamp
	@Column(nullable = false)
	private LocalDateTime updateAt = LocalDateTime.now();
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getOrigin() {
		return origin;
	}

	public void setOrigin(User origin) {
		this.origin = origin;
	}

	public String getOriginId() {
		return originId;
	}

	public void setOriginId(String originId) {
		this.originId = originId;
	}

	public User getTarget() {
		return target;
	}

	public void setTarget(User target) {
		this.target = target;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public LocalDateTime getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}

}
