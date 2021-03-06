package com.smart.community.wsyjavabean;

import java.util.List;

public class TableBean
{
	private int code;
	private String msg;
	private int count;   //计算页数
	private Object data;//返回数据
	private int start; //起始页
	private int limit;
	private int page; //当前页数
	private List list;//接收页数
	//客户管理
	private String owner_name;//业主姓名
	private String owner_tel; //业主手机号码
	private String owner_status;//房间状态
	private String owner_identity;//业主身份证号码
	//治安管理
	private String safe_event_title;//安全事件标题
	private String safe_event_date;//治安事件发生日期
	private String startDate;//起始发生的时间
	private String endDate;//结束发生的时间
	//参数管理
	private String parameter_name;//参数名
	private String manifest_number;//货物单号
	private String manifest_name;//货物名称
	public int manifest_quantity;//货物数量
	public String counNum;
	//日志管理
	private String operation_person;//日志操作人员
	private String operation_date;//日志操作日期
	private String operation_time;//日志操作时间
	private String startTime;
	private String endTime;
    //采购管理
	private String purchase_name;//采购商品名
	private String purchase_model;//商品型号
    //合同管理
	private String ontract_name;//合同名称
	private String ontract_time;//合同上传时间
    //设备管理
	private String fire_tools_name;//设备名称
	private String fire_tools_classification;//设备分类
	private String buy_date;//设备购买时间
	//付款
	private String payment_type;//付款类型
	private String payment_time;//付款时间
	private String payment_remarks;//付款备注
    //收款
	private String receivables_type; //收款类型
	private String receivables_remarks;//收款金额
	private String receivables_time;//收款时间
	//对账
	private String accountMoney; //收支金额
	private String accountTime;  //收支时间
	private String accountType; //收支类型
	private String accountRemark; //收支备注
	private String startMonry;//起始金额
	private String endMoney; //结束金额

	public TableBean()
	{
	}

	public TableBean(int code, String msg, int count, Object data, int start, int limit, int page, List list, String owner_name, String owner_tel, String owner_status, String owner_identity, String safe_event_title, String safe_event_date, String startDate, String endDate, String parameter_name, String manifest_number, String manifest_name, int manifest_quantity, String counNum, String operation_person, String operation_date, String operation_time, String startTime, String endTime, String purchase_name, String purchase_model, String ontract_name, String ontract_time, String fire_tools_name, String fire_tools_classification, String buy_date, String payment_type, String payment_time, String payment_remarks, String receivables_type, String receivables_remarks, String receivables_time, String accountMoney, String accountTime, String accountType, String accountRemark, String startMonry, String endMoney)
	{
		this.code = code;
		this.msg = msg;
		this.count = count;
		this.data = data;
		this.start = start;
		this.limit = limit;
		this.page = page;
		this.list = list;
		this.owner_name = owner_name;
		this.owner_tel = owner_tel;
		this.owner_status = owner_status;
		this.owner_identity = owner_identity;
		this.safe_event_title = safe_event_title;
		this.safe_event_date = safe_event_date;
		this.startDate = startDate;
		this.endDate = endDate;
		this.parameter_name = parameter_name;
		this.manifest_number = manifest_number;
		this.manifest_name = manifest_name;
		this.manifest_quantity = manifest_quantity;
		this.counNum = counNum;
		this.operation_person = operation_person;
		this.operation_date = operation_date;
		this.operation_time = operation_time;
		this.startTime = startTime;
		this.endTime = endTime;
		this.purchase_name = purchase_name;
		this.purchase_model = purchase_model;
		this.ontract_name = ontract_name;
		this.ontract_time = ontract_time;
		this.fire_tools_name = fire_tools_name;
		this.fire_tools_classification = fire_tools_classification;
		this.buy_date = buy_date;
		this.payment_type = payment_type;
		this.payment_time = payment_time;
		this.payment_remarks = payment_remarks;
		this.receivables_type = receivables_type;
		this.receivables_remarks = receivables_remarks;
		this.receivables_time = receivables_time;
		this.accountMoney = accountMoney;
		this.accountTime = accountTime;
		this.accountType = accountType;
		this.accountRemark = accountRemark;
		this.startMonry = startMonry;
		this.endMoney = endMoney;
	}

	@Override
	public String toString()
	{
		return "TableBean{" + "code=" + code + ", msg='" + msg + '\'' + ", count=" + count + ", data=" + data + ", start=" + start + ", limit=" + limit + ", page=" + page + ", list=" + list + ", owner_name='" + owner_name + '\'' + ", owner_tel='" + owner_tel + '\'' + ", owner_status='" + owner_status + '\'' + ", owner_identity='" + owner_identity + '\'' + ", safe_event_title='" + safe_event_title + '\'' + ", safe_event_date='" + safe_event_date + '\'' + ", startDate='" + startDate + '\'' + ", endDate='" + endDate + '\'' + ", parameter_name='" + parameter_name + '\'' + ", manifest_number='" + manifest_number + '\'' + ", manifest_name='" + manifest_name + '\'' + ", manifest_quantity=" + manifest_quantity + ", counNum='" + counNum + '\'' + ", operation_person='" + operation_person + '\'' + ", operation_date='" + operation_date + '\'' + ", operation_time='" + operation_time + '\'' + ", startTime='" + startTime + '\'' + ", endTime='" + endTime + '\'' + ", purchase_name='" + purchase_name + '\'' + ", purchase_model='" + purchase_model + '\'' + ", ontract_name='" + ontract_name + '\'' + ", ontract_time='" + ontract_time + '\'' + ", fire_tools_name='" + fire_tools_name + '\'' + ", fire_tools_classification='" + fire_tools_classification + '\'' + ", buy_date='" + buy_date + '\'' + ", payment_type='" + payment_type + '\'' + ", payment_time='" + payment_time + '\'' + ", payment_remarks='" + payment_remarks + '\'' + ", receivables_type='" + receivables_type + '\'' + ", receivables_remarks='" + receivables_remarks + '\'' + ", receivables_time='" + receivables_time + '\'' + ", accountMoney='" + accountMoney + '\'' + ", accountTime='" + accountTime + '\'' + ", accountType='" + accountType + '\'' + ", accountRemark='" + accountRemark + '\'' + ", startMonry='" + startMonry + '\'' + ", endMoney='" + endMoney + '\'' + '}';
	}

	public int getCode()
	{
		return code;
	}

	public void setCode(int code)
	{
		this.code = code;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}

	public int getStart()
	{
		return start;
	}

	public void setStart(int start)
	{
		this.start = start;
	}

	public int getLimit()
	{
		return limit;
	}

	public void setLimit(int limit)
	{
		this.limit = limit;
	}

	public int getPage()
	{
		return page;
	}

	public void setPage(int page)
	{
		this.page = page;
	}

	public List getList()
	{
		return list;
	}

	public void setList(List list)
	{
		this.list = list;
	}

	public String getOwner_name()
	{
		return owner_name;
	}

	public void setOwner_name(String owner_name)
	{
		this.owner_name = owner_name;
	}

	public String getOwner_tel()
	{
		return owner_tel;
	}

	public void setOwner_tel(String owner_tel)
	{
		this.owner_tel = owner_tel;
	}

	public String getOwner_status()
	{
		return owner_status;
	}

	public void setOwner_status(String owner_status)
	{
		this.owner_status = owner_status;
	}

	public String getOwner_identity()
	{
		return owner_identity;
	}

	public void setOwner_identity(String owner_identity)
	{
		this.owner_identity = owner_identity;
	}

	public String getSafe_event_title()
	{
		return safe_event_title;
	}

	public void setSafe_event_title(String safe_event_title)
	{
		this.safe_event_title = safe_event_title;
	}

	public String getSafe_event_date()
	{
		return safe_event_date;
	}

	public void setSafe_event_date(String safe_event_date)
	{
		this.safe_event_date = safe_event_date;
	}

	public String getStartDate()
	{
		return startDate;
	}

	public void setStartDate(String startDate)
	{
		this.startDate = startDate;
	}

	public String getEndDate()
	{
		return endDate;
	}

	public void setEndDate(String endDate)
	{
		this.endDate = endDate;
	}

	public String getParameter_name()
	{
		return parameter_name;
	}

	public void setParameter_name(String parameter_name)
	{
		this.parameter_name = parameter_name;
	}

	public String getManifest_number()
	{
		return manifest_number;
	}

	public void setManifest_number(String manifest_number)
	{
		this.manifest_number = manifest_number;
	}

	public String getManifest_name()
	{
		return manifest_name;
	}

	public void setManifest_name(String manifest_name)
	{
		this.manifest_name = manifest_name;
	}

	public int getManifest_quantity()
	{
		return manifest_quantity;
	}

	public void setManifest_quantity(int manifest_quantity)
	{
		this.manifest_quantity = manifest_quantity;
	}

	public String getCounNum()
	{
		return counNum;
	}

	public void setCounNum(String counNum)
	{
		this.counNum = counNum;
	}

	public String getOperation_person()
	{
		return operation_person;
	}

	public void setOperation_person(String operation_person)
	{
		this.operation_person = operation_person;
	}

	public String getOperation_date()
	{
		return operation_date;
	}

	public void setOperation_date(String operation_date)
	{
		this.operation_date = operation_date;
	}

	public String getOperation_time()
	{
		return operation_time;
	}

	public void setOperation_time(String operation_time)
	{
		this.operation_time = operation_time;
	}

	public String getStartTime()
	{
		return startTime;
	}

	public void setStartTime(String startTime)
	{
		this.startTime = startTime;
	}

	public String getEndTime()
	{
		return endTime;
	}

	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}

	public String getPurchase_name()
	{
		return purchase_name;
	}

	public void setPurchase_name(String purchase_name)
	{
		this.purchase_name = purchase_name;
	}

	public String getPurchase_model()
	{
		return purchase_model;
	}

	public void setPurchase_model(String purchase_model)
	{
		this.purchase_model = purchase_model;
	}

	public String getOntract_name()
	{
		return ontract_name;
	}

	public void setOntract_name(String ontract_name)
	{
		this.ontract_name = ontract_name;
	}

	public String getOntract_time()
	{
		return ontract_time;
	}

	public void setOntract_time(String ontract_time)
	{
		this.ontract_time = ontract_time;
	}

	public String getFire_tools_name()
	{
		return fire_tools_name;
	}

	public void setFire_tools_name(String fire_tools_name)
	{
		this.fire_tools_name = fire_tools_name;
	}

	public String getFire_tools_classification()
	{
		return fire_tools_classification;
	}

	public void setFire_tools_classification(String fire_tools_classification)
	{
		this.fire_tools_classification = fire_tools_classification;
	}

	public String getBuy_date()
	{
		return buy_date;
	}

	public void setBuy_date(String buy_date)
	{
		this.buy_date = buy_date;
	}

	public String getPayment_type()
	{
		return payment_type;
	}

	public void setPayment_type(String payment_type)
	{
		this.payment_type = payment_type;
	}

	public String getPayment_time()
	{
		return payment_time;
	}

	public void setPayment_time(String payment_time)
	{
		this.payment_time = payment_time;
	}

	public String getPayment_remarks()
	{
		return payment_remarks;
	}

	public void setPayment_remarks(String payment_remarks)
	{
		this.payment_remarks = payment_remarks;
	}

	public String getReceivables_type()
	{
		return receivables_type;
	}

	public void setReceivables_type(String receivables_type)
	{
		this.receivables_type = receivables_type;
	}

	public String getReceivables_remarks()
	{
		return receivables_remarks;
	}

	public void setReceivables_remarks(String receivables_remarks)
	{
		this.receivables_remarks = receivables_remarks;
	}

	public String getReceivables_time()
	{
		return receivables_time;
	}

	public void setReceivables_time(String receivables_time)
	{
		this.receivables_time = receivables_time;
	}

	public String getAccountMoney()
	{
		return accountMoney;
	}

	public void setAccountMoney(String accountMoney)
	{
		this.accountMoney = accountMoney;
	}

	public String getAccountTime()
	{
		return accountTime;
	}

	public void setAccountTime(String accountTime)
	{
		this.accountTime = accountTime;
	}

	public String getAccountType()
	{
		return accountType;
	}

	public void setAccountType(String accountType)
	{
		this.accountType = accountType;
	}

	public String getAccountRemark()
	{
		return accountRemark;
	}

	public void setAccountRemark(String accountRemark)
	{
		this.accountRemark = accountRemark;
	}

	public String getStartMonry()
	{
		return startMonry;
	}

	public void setStartMonry(String startMonry)
	{
		this.startMonry = startMonry;
	}

	public String getEndMoney()
	{
		return endMoney;
	}

	public void setEndMoney(String endMoney)
	{
		this.endMoney = endMoney;
	}
}
