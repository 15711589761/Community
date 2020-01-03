//根据日期，获取年龄

function getAge(strBirthday) {
	var returnAge;
	var strBirthdayArr = strBirthday.split("-");
	var birthYear = strBirthdayArr[0];
	var birthMonth = strBirthdayArr[1];
	var birthDay = strBirthdayArr[2];
	var d = new Date();
	var nowYear = d.getFullYear();
	var nowMonth = d.getMonth() + 1;
	var nowDay = d.getDate();
	if (nowYear === birthYear) {
		returnAge = 0;//同年 则为0岁
	} else {
		var ageDiff = nowYear - birthYear; //年之差
		if (ageDiff > 0) {
			if (nowMonth === birthMonth) {
				var dayDiff = nowDay - birthDay;//日之差
				if (dayDiff < 0) {
					returnAge = ageDiff - 1;
				} else {
					returnAge = ageDiff;
				}
			} else {
				var monthDiff = nowMonth - birthMonth;//月之差
				if (monthDiff < 0) {
					returnAge = ageDiff - 1;
				} else {
					returnAge = ageDiff;
				}
			}
		} else {
			returnAge = -1;//返回-1 表示出生日期输入错误 晚于今天
		}
	}

	return returnAge;//返回周岁年龄

}
//判断选择的日期是否大于当前日期
function dayIsExceed(strDay) {
	var d = new Date(Date.parse(strDay.replace(/-/g, "/")));
	var today = new Date();
	return d > today;
}
//手机号码的验证（包含固定电话）
function checkNumber(value) {
	var reg =/^((0\d{2,3}-\d{7,8})|(1[3456789]\d{9}))$/;
	return reg.test(value)
}

//获取当前日期
function getNowDate(date)
{
	return formatDate(date);
}

//获取时间
function getTime(date) {
	var h = date.getHours();
	var m = date.getMinutes();
	var s = date.getSeconds();
	if (h < 10) h = "0" + h;
	if (m < 10) m = "0" + m;
	if (s < 10) s = "0" + s;
	return h+":"+m+":"+s;
}

//格式化日期
function formatDate(date)
{
	var myYear = date.getFullYear();
	var myMonth = date.getMonth() + 1;
	var myWeekday = date.getDate();
	if (myMonth < 10)
	{
		myMonth = "0" + myMonth;
	}
	if (myWeekday < 10)
	{
		myWeekday = "0" + myWeekday;
	}
	return (myYear + "-" + myMonth + "-" + myWeekday);
}

//获得某月的天数
function getMonthDays(nowYear,myMonth)
{
	var monthStartDate = new Date(nowYear, myMonth, 1);
	var monthEndDate = new Date(nowYear, myMonth + 1, 1);
	return (monthEndDate - monthStartDate) / (1000 * 60 * 60 * 24);//格式转换;
}
//获得本月的开始日期
function getMonthStartDate (date)
{
	var theYear = date.getFullYear();//声明变量Year 获取当前时间的年
	var theMonth = date.getMonth();//声明变量Month 获取当前时间的月
	var monthStartDate = new Date(theYear, theMonth,1);
	return formatDate(monthStartDate);//返回当月第一天
}

//获得本月的结束日期
function getMonthEndDate  (date)
{
	var theYear = date.getFullYear();//声明变量Year 获取当前时间的年
	var theMonth = date.getMonth();//声明变量Month 获取当前时间的月
	var dayCount= getMonthDays(theYear,theMonth);//获取当月总共有多少天
	var monthEndDate = new Date(theYear,theMonth, dayCount);
	return formatDate(monthEndDate);//返回当月结束时间
}

