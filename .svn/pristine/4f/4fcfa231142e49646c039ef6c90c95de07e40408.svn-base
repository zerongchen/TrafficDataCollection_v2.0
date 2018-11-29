	$(document).ready(function() {
		/**
		 * form validate start
		 */
	    $("#loginform").validate({
	        rules : {
	            username: {
	                required: true,
	                maxlength:20
	            },
	            password: {
	            	required: true,
	            	maxlength:20
	            },
	            checkCode: {
	            	required: true,
	            	maxlength:4,
	            	minlength:4
	            }
	        },
	        messages: {
	        	username: {
	                required: "请输入用户名",
	                maxlength:$.validator.format("不能超过{0}个字符")
	            },
	            password: {
	                required: "请输入密码",
	                maxlength:$.validator.format("不能超过{0}个字符")
	            },
	            checkCode: {
	                required: "请输入验证码",
	                maxlength:$.validator.format("不能超过{0}个字符"),
	                minlength:$.validator.format("请输入完整验证码")
	            }
	        },
	        errorPlacement: function(error, element) {
                error.appendTo( element.parent().next() );
	        },
	        submitHandler:function(form){
	        	login();
	        }
	    });
	    /**
	     * form validate end
	     */
		reloadImage();
		$("input").keydown(function(event){
			if(event.keyCode==13){
				$("#loginform").submit();
			}
		});
	});
	var key ;
	function bodyRSA(){
	    setMaxDigits(130);
	    key = new RSAKeyPair("10001","","bc64dad7e37c54ab63c830955ac81c0b61aa2a0d9041026cb5743139950f5c9b6c97e71aede8c9957b1a18542a11f4bdf64268687da511a0c4b96b11e260245ad1fd0987c948c9c9dde5ad2d80191cfe8ae429107061ba7102e50de8126523a175466e03d899d875e666bc4c8bb752ded01edb1a7d67dae1c5a50c42c19272d3");
	}
	
	function login(){
		
		bodyRSA();
		var clearText = encodeURIComponent(randomChar(4)+ $.trim($("#j_username").val()) + "*|*" + $.trim($("#checkCode").val()) + "*|*" +hex_md5($.trim($("#j_password").val())));
   		var sk = encryptedString(key, clearText);
   		
		$.ajax({
			type: "POST",
			url: url_login,
			data: "sk=" + sk,
			contentType: "application/x-www-form-urlencoded; charset=utf-8",//发送到服务器的数据类型
			dataType: "text",//预期服务器返回数据类型
			beforeSend: function(){
			  document.getElementById("loginButton").disabled = true;
			  document.getElementById("j_username").disabled = true;
			  document.getElementById("j_password").disabled = true;
			  document.getElementById("checkCode").disabled = true;
			  $("#error").text("认证中，请稍候……");
			},
			success: function(msg){
				if(msg != null && $.trim(msg) != ""){
					$("#error").text(msg);
					document.getElementById("loginButton").disabled = false;
					document.getElementById("j_username").disabled = false;
					document.getElementById("j_password").disabled = false;
					document.getElementById("checkCode").disabled = false;
	  				reloadImage();
					return ;
				}
				$("#error").text("认证通过,转向用户操作页面……");
				window.location = url_welcome;	
			}
	  	});
	}
	// 刷新验证码
	function reloadImage(){
	    $("#checkcode").attr("src",ctx+"/common/image?rand="+Math.random());
	}