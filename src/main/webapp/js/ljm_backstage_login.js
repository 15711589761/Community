$(function () {
	var imgNode = $("#backstage_login_password");
	getVerify(imgNode);
	var loginError = $("#backstage_login_fail_massage_error").val();
	var loginVerification = $("#backstage_login_fail_massage_verification").val();
	var loginAuthority = $("#backstage_login_fail_massage_authority").val();
	var loginIsEmpty = $("#backstage_login_fail_massage_isEmpty").val();
	if (null != loginError && loginError.length > 0)
	{
		alert(loginError);
	} else if (null != loginVerification && loginVerification.length > 0)
	{
		alert(loginVerification);
	} else if (null != loginAuthority && loginAuthority.length > 0)
	{
		alert(loginAuthority);
	} else if (null != loginIsEmpty && loginIsEmpty.length > 0)
	{
		alert(loginIsEmpty);
	}
});

function getVerify(obj){
	obj.src ="/Community/getVerify.image?"+Math.random();
}

function isEmptyInput() {
	var loginName = $("#backstage_login_name").val();
	var loginPassWord = $("#backstage_login_password").val();
	var imageCode = $("#backstage_login_image_code").val();
	if ( loginName.length > 0 )
	{
		if ( loginPassWord.length > 0 )
		{
			if (imageCode.length > 0)
			{
				$("#backstage_login_password").val(md5(loginPassWord));
				return true;
			}else {
				alert("请输入验证码！");
				return false;
			}
		}else {
			alert("密码不能为空！");
			return false;
		}
	}else {
		alert("用户名不能为空！");
		return false;
	}
}