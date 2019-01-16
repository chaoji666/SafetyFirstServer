package com.linzx.web.safetyfirst.push.bean.db;

import java.security.Principal;
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
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * 用户的model
 * @author linzx
 * 2018年12月24日
 */
@Entity
@Table(name = "TB_USER")
public class User implements Principal{

	// 这是一个主键
	@Id
	@PrimaryKeyJoinColumn
	// 主键生成存储的类型为uuid
	@GeneratedValue(generator = "uuid")
	// 把uuid的生成器定义为uuid2，uuid2是常规的uuid tostring
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	// 不允许更改，不允许为null
	@Column(updatable = false, nullable = false)
	private String id;

	// 用户名必须唯一
	@Column(nullable = false, length = 128, unique = true)
	private String name;

	// 电话必须唯一
	@Column(nullable = false, length = 62, unique = true)
	private String phone;

	// 密码
	@Column(nullable = false)
	private String password;

	// 头像允许为null
	@Column
	private String portrait;

	// 描述
	@Column
	private String description;

	// 性别有初始值，所以不为空
	@Column(nullable = false)
	private int sex = 0;

	// token可以拉取用户信息，所以token必须唯一
	@Column(unique = true)
	private String token;

	// 用于推送的设备ID
	@Column
	private String pushId;

	// 定义为创建时间戳，在创建时就已经写入
	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime createAt = LocalDateTime.now();

	// 定义为更新时间戳，在创建时就已经写入
	@UpdateTimestamp
	@Column(nullable = false)
	private LocalDateTime updateAt = LocalDateTime.now();

	// 最后一次收到消息的时间
	@Column
	private LocalDateTime lastReceivedAt = LocalDateTime.now();
	
	//我关注的人的列表方法
	//对应的数据库表字段为TB_USER_FOLLOW。originId
	@JoinColumn(name="originId")
	//定义为懒加载,默认加载User信息的时候，并不查询这个集合
	@LazyCollection(LazyCollectionOption.EXTRA)
	//一对多，一个用户可以有很多关注人
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private Set<UserFollow> following = new HashSet<>();
	
	//关注我的人的列表
	//对应的数据库表字段为TB_USER_FOLLOW。targetId
	@JoinColumn(name="targetId")
	//定义为懒加载,默认加载User信息的时候，并不查询这个集合
	@LazyCollection(LazyCollectionOption.EXTRA)
	//一对多，一个用户可以被很多人关注，每次关注都是一个记录
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private Set<UserFollow> followers = new HashSet<>();
	
	//我所有创建的群
	//对应的字段为Group。ownerId
	@JoinColumn(name="ownerId")
	//懒加载集合方式，尽可能的不加载具体的数据，
	//当访问groups。size()仅仅查询数量，不加载具体的group信息
	//只有当遍历集合的时候才加载具体的数据
	@LazyCollection(LazyCollectionOption.EXTRA)
	//FetchType.LAZY:懒加载，加载用户信息时不加载这个集合
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private Set<Group> groups = new HashSet<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPortrait() {
		return portrait;
	}

	public void setPortrait(String portrait) {
		this.portrait = portrait;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPushId() {
		return pushId;
	}

	public void setPushId(String pushId) {
		this.pushId = pushId;
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

	public LocalDateTime getLastReceivedAt() {
		return lastReceivedAt;
	}

	public void setLastReceivedAt(LocalDateTime lastReceivedAt) {
		this.lastReceivedAt = lastReceivedAt;
	}

	public Set<UserFollow> getFollowing() {
		return following;
	}

	public void setFollowing(Set<UserFollow> following) {
		this.following = following;
	}

	public Set<UserFollow> getFollowers() {
		return followers;
	}

	public void setFollowers(Set<UserFollow> followers) {
		this.followers = followers;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	
}
