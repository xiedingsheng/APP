package com.dcits.storage.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 出库单实体类
 * @author xieds
 * @date 2019/4/10 9:29
 * @updater xieds
 * @updatedate 2019/4/10 9:29
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Table(name = "TU_OUT_STORE")
@Accessors(chain = true)
@Setter
@Getter
@Entity
@NamedStoredProcedureQueries({
		@NamedStoredProcedureQuery(name = "PR_OUTSTORE_RELATE", procedureName = "PR_OUTSTORE_RELATE", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "OUT_STORE_NO", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "USER_NAME", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.OUT, name = "MESSAGE", type = String.class) }),
		@NamedStoredProcedureQuery(name = "PR_OUTSTORE_CONFIRM", procedureName = "PR_OUTSTORE_CONFIRM", parameters = {
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "OUT_STORE_NO", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.IN, name = "USER_NAME", type = String.class),
			@StoredProcedureParameter(mode = ParameterMode.OUT, name = "MESSAGE", type = String.class)}) })
public class TuOutStore implements Serializable {

	/**
	 * 主键，GUID，系统自动生成
	 * TU_OUT_STORE.ID
	 */
	@Id
	@Column(name = "ID")
	private String id;

	/**
	 * 出库单号，系统自动生成
	 * TU_OUT_STORE.OUT_STORE_NO
	 */
	@Column(name = "OUT_STORE_NO")
	private String outStoreNo;

	/**
	 * 出口口岸代码
	 * TU_OUT_STORE.IE_PORT
	 */
	@Column(name = "IE_PORT")
	private String iePort;

	/**
	 * 车牌号
	 * TU_OUT_STORE.CAR_NO
	 */
	@Column(name = "CAR_NO")
	private String carNo;

	/**
	 * 集装箱号
	 * TU_OUT_STORE.BOX_NO
	 */
	@Column(name = "BOX_NO")
	private String boxNo;

	/**
	 * 箱型 20,40
	 * TU_OUT_STORE.BOX_TYPE
	 */
	@Column(name = "BOX_TYPE")
	private String boxType;

	/**
	 * 状态 0预录入 1出库申报 2出库关
	 * TU_OUT_STORE.STATUS
	 */
	@Column(name = "STATUS")
	private String status;

	/**
	 * 录入人代码
	 * TU_OUT_STORE.INPUT_USER
	 */
	@Column(name = "INPUT_USER")
	private String inputUser;

	/**
	 * 录入时间
	 * TU_OUT_STORE.INPUT_TIME
	 */
	@Column(name = "INPUT_TIME")
	private Date inputTime;

	/**
	 * 出库申报人代码
	 * TU_OUT_STORE.APPLY_USER
	 */
	@Column(name = "APPLY_USER")
	private String applyUser;

	/**
	 * 出库申报时间
	 * TU_OUT_STORE.APPLY_TIME
	 */
	@Column(name = "APPLY_TIME")
	private Date applyTime;

	/**
	 * 出库关联人代码
	 * TU_OUT_STORE.RELATION_USER
	 */
	@Column(name = "RELATION_USER")
	private String relationUser;

	/**
	 * 出库关联时间
	 * TU_OUT_STORE.RELATION_TIME
	 */
	@Column(name = "RELATION_TIME")
	private Date relationTime;

	/**
	 * 出库确认人代码
	 * TU_OUT_STORE.CONFIRM_USER
	 */
	@Column(name = "CONFIRM_USER")
	private String confirmUser;

	/**
	 * 出库确认时间
	 * TU_OUT_STORE.CONFIRM_TIME
	 */
	@Column(name = "CONFIRM_TIME")
	private Date confirmTime;

	/**
	 * 备注
	 * TU_OUT_STORE.REMARK
	 */
	@Column(name = "REMARK")
	private String remark;

	/**
	 * 目的港代码
	 * TU_OUT_STORE.DESTINATION_PORT
	 */
	@Column(name = "DESTINATION_PORT")
	private String destinationPort;

	/**
	 * 供应商代码
	 * TU_OUT_STORE.SUPPLIER_CODE
	 */
	@Column(name = "SUPPLIER_CODE")
	private String supplierCode;

	/**
	 * 供应商名称
	 * TU_OUT_STORE.SUPPLIER_NAME
	 */
	@Column(name = "SUPPLIER_NAME")
	private String supplierName;

	/**
	 * 录入单位代码
	 * TU_OUT_STORE.INPUT_COMP_CODE
	 */
	@Column(name = "INPUT_COMP_CODE")
	private String inputCompCode;

	/**
	 * 录入单位名称
	 * TU_OUT_STORE.INPUT_COMP_NAME
	 */
	@Column(name = "INPUT_COMP_NAME")
	private String inputCompName;

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
