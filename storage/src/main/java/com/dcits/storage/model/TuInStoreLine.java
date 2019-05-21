package com.dcits.storage.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 入库单明细实体类
 * @author xieds
 * @date 2019/4/10 9:29
 * @updater xieds
 * @updatedate 2019/4/10 9:29
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Table(name = "TU_IN_STORE_LINE")
@Accessors(chain = true)
@Setter
@Getter
@Entity
public class TuInStoreLine implements Serializable {

	/**
	 * 主键，GUID，系统自动生成
	 * TU_IN_STORE_LINE.ID
	 */
	@Id
	@Column(name = "ID")
	private String id;

	/**
	 * 入库单号
	 * TU_IN_STORE_LINE.IN_STORE_NO
	 */
	@Column(name = "IN_STORE_NO")
	private String inStoreNo;

	/**
	 * 税则号
	 * TU_IN_STORE_LINE.HSCODE
	 */
	@Column(name = "HSCODE")
	private String hscode;

	/**
	 * 品名
	 * TU_IN_STORE_LINE.CDESC
	 */
	@Column(name = "CDESC")
	private String cdesc;

	/**
	 * 型号
	 * TU_IN_STORE_LINE.MODEL
	 */
	@Column(name = "MODEL")
	private String model;

	/**
	 * 数量
	 * TU_IN_STORE_LINE.QTY
	 */
	@Column(name = "QTY")
	private Double qty;

	/**
	 * 重量（KG）
	 * TU_IN_STORE_LINE.WEIGHT
	 */
	@Column(name = "WEIGHT")
	private Double weight;

	@Column(name = "create_date")
	private Date createDate;

	@Column(name = "creator")
	private String creator;

	@Column(name = "is_deleted")
	private String isDeleted;

	@Column(name = "update_date")
	private Date updateDate;

	@Column(name = "updater")
	private String updater;

	@Column(name = "version")
	private Integer version;

	private static final long serialVersionUID = 1L;
}
