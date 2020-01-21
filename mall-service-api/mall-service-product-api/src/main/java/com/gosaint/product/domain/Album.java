package com.gosaint.product.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

/****
 * @Author:gosaint
 * @Description:Album构建
*/
@ApiModel(description = "Album",value = "Album")
@Table(name="tb_album")
public class Album implements Serializable{

	@ApiModelProperty(value = "编号",required = false)
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	/** 编号*/
	private Long id;
	@ApiModelProperty(value = "相册名称",required = false)
    @Column(name = "title")
	/** 相册名称*/
	private String title;
	@ApiModelProperty(value = "相册封面",required = false)
    @Column(name = "image")
	/** 相册封面*/
	private String image;
	@ApiModelProperty(value = "图片列表",required = false)
    @Column(name = "image_items")
	/** 图片列表*/
	private String imageItems;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(final String image) {
		this.image = image;
	}

	public String getImageItems() {
		return imageItems;
	}

	public void setImageItems(final String imageItems) {
		this.imageItems = imageItems;
	}
}
