package com.smart.community.wsyjavabean;

public class Tbl_safeEvent
{
	private int safe_event_id;        //治安事件记录id
	private String safe_event_title;  //安全事件标题
	private String safe_event_context;//治安事件内容
	private String safe_event_date;   //治安事件发生日期
	private String safe_event_recorder;//治安事件记录人

	public Tbl_safeEvent(int safe_event_id, String safe_event_title, String safe_event_context, String safe_event_date, String safe_event_recorder)
	{
		this.safe_event_id = safe_event_id;
		this.safe_event_title = safe_event_title;
		this.safe_event_context = safe_event_context;
		this.safe_event_date = safe_event_date;
		this.safe_event_recorder = safe_event_recorder;
	}

	public Tbl_safeEvent()
	{
	}

	@Override
	public String toString()
	{
		return "Tbl_safeEvent{" + "safe_event_id=" + safe_event_id + ", safe_event_title='" + safe_event_title + '\'' + ", safe_event_context='" + safe_event_context + '\'' + ", safe_event_date='" + safe_event_date + '\'' + ", safe_event_recorder='" + safe_event_recorder + '\'' + '}';
	}

	public int getSafe_event_id()
	{
		return safe_event_id;
	}

	public void setSafe_event_id(int safe_event_id)
	{
		this.safe_event_id = safe_event_id;
	}

	public String getSafe_event_title()
	{
		return safe_event_title;
	}

	public void setSafe_event_title(String safe_event_title)
	{
		this.safe_event_title = safe_event_title;
	}

	public String getSafe_event_context()
	{
		return safe_event_context;
	}

	public void setSafe_event_context(String safe_event_context)
	{
		this.safe_event_context = safe_event_context;
	}

	public String getSafe_event_date()
	{
		return safe_event_date;
	}

	public void setSafe_event_date(String safe_event_date)
	{
		this.safe_event_date = safe_event_date;
	}

	public String getSafe_event_recorder()
	{
		return safe_event_recorder;
	}

	public void setSafe_event_recorder(String safe_event_recorder)
	{
		this.safe_event_recorder = safe_event_recorder;
	}
}
